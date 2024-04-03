package com.example.finalprodproject.feature.study_page.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalprodproject.common.coreui.cources_category_item.CoursesCategoryItemDelegateAdapter
import com.example.finalprodproject.databinding.StudyFragmentBinding
import com.example.finalprodproject.feature.study_page.data.repository.StudyRepository
import com.example.finalprodproject.feature.study_page.presentation.mapper.CoursesDataMapper.mapToViewModelByCategories
import com.example.finalprodproject.utils.adapter.CompositeAdapter

class StudyFragment : Fragment() {

    private var _binding: StudyFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModelFactory by lazy {
        StudyViewModelFactory(
            StudyRepository(
                context = requireContext()
            )
        )
    }
    private val viewModel: StudyViewModel by viewModels { viewModelFactory }

    private val coursesCompositeAdapter by lazy {
        CompositeAdapter.Builder()
            .add(CoursesCategoryItemDelegateAdapter { _, courseItemViewModel ->
                val direction: NavDirections = with(courseItemViewModel) {
                    if (!started && !explored) {
                        StudyFragmentDirections.actionStudyFragmentToStudyInformationFragment(
                            courseItemViewModel.id
                        )
                    } else if (started && !explored) {
                        StudyFragmentDirections.actionStudyFragmentToRoadmapFragment(
                            courseItemViewModel.id
                        )
                    } else {
                        StudyFragmentDirections.actionStudyFragmentToCourseEndFragment(
                            courseItemViewModel.title,
                            courseItemViewModel.points
                        )
                    }
                }
                findNavController().navigate(direction)
            })
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = StudyFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        if (viewModel.allCoursesData.value == null) {
            viewModel.allCoursesData.observe(viewLifecycleOwner) { allCourses ->
                viewModel.comparedList?.let { comparedList ->
                    coursesCompositeAdapter.submitList(comparedList.mapToViewModelByCategories())
                }
            }
        } else {
            viewModel.clearAllCourses()
        }
        if (viewModel.myCoursesData.value == null) {
            viewModel.myCoursesData.observe(viewLifecycleOwner) {
                viewModel.comparedList?.let { comparedList ->
                    coursesCompositeAdapter.submitList(comparedList.mapToViewModelByCategories())
                }
            }
        } else {
            viewModel.clearMyCourses()
        }
        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean { return false }
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (!newText.isNullOrEmpty()) {
                        viewModel.comparedList?.let { comparedList ->
                            coursesCompositeAdapter.submitList(
                                comparedList.mapToViewModelByCategories().filter {
                                    it.title.lowercase().split(" ").any { word -> word.startsWith(newText) }
                                }
                            )
                        }
                    } else {
                        viewModel.comparedList?.let {
                            coursesCompositeAdapter.submitList(it.mapToViewModelByCategories())
                        }
                    }
                    return true
                }
            }
        )
        viewModel.loadAllCourses()
        viewModel.loadMyCourses()
    }

    private fun initAdapter() {
        binding.mainRecycler.apply {
            layoutManager = LinearLayoutManager(context).apply { setOrientation(LinearLayoutManager.VERTICAL) }
            adapter = coursesCompositeAdapter
        }
    }

}
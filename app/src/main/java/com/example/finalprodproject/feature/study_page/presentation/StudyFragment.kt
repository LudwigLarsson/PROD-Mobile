package com.example.finalprodproject.feature.study_page.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalprodproject.R
import com.example.finalprodproject.common.coreui.cources_category_item.CoursesCategoryItemDelegateAdapter
import com.example.finalprodproject.common.coreui.cources_category_item.CoursesCategoryItemViewModel
import com.example.finalprodproject.databinding.StudyFragmentBinding
import com.example.finalprodproject.feature.study_page.data.repository.StudyRepository
import com.example.finalprodproject.feature.study_page.presentation.mapper.CoursesDataMapper.mapToViewModelByCategories
import com.example.finalprodproject.feature.study_page.presentation.mapper.CoursesDataMapper.mapToViewModelsList
import com.example.finalprodproject.utils.adapter.CompositeAdapter
import com.example.finalprodproject.utils.adapter.CompositeAdapterUtils.addAllToTheEnd
import com.example.finalprodproject.utils.adapter.CompositeAdapterUtils.addAllToTheStart

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
                if (!allCourses.isNullOrEmpty()) {
                    coursesCompositeAdapter.addAllToTheEnd(allCourses.mapToViewModelByCategories())
                }
            }
        } else {
            viewModel.clearAllCourses()
        }
        if (viewModel.myCoursesData.value == null) {
            viewModel.myCoursesData.observe(viewLifecycleOwner) { myCourses ->
                if (!myCourses.isNullOrEmpty()) {
                    val myCoursesItemViewModel = CoursesCategoryItemViewModel(
                        title = requireContext().resources.getString(R.string.courses),
                        courses = myCourses.mapToViewModelsList()
                    )

                    coursesCompositeAdapter.addAllToTheStart(mutableListOf(myCoursesItemViewModel))
                }
            }
        } else {
            viewModel.clearMyCourses()
        }
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
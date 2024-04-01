package com.example.finalprodproject.feature.study_page.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

    private val allCoursesCompositeAdapter by lazy {
        CompositeAdapter.Builder()
            .add(CoursesCategoryItemDelegateAdapter())
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
        viewModel.allCoursesData.observe(viewLifecycleOwner) { allCourses ->
            allCoursesCompositeAdapter.submitList(
                allCourses.mapToViewModelByCategories()
            )
        }

        initAdapter()
        viewModel.loadAllCourses()
    }

    private fun initAdapter() {
        binding.mainRecycler.apply {
            layoutManager = LinearLayoutManager(context).apply { setOrientation(LinearLayoutManager.VERTICAL) }
            adapter = allCoursesCompositeAdapter
        }
    }

}
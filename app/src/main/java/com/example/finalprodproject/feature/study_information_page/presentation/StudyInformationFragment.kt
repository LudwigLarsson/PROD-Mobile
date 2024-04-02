package com.example.finalprodproject.feature.study_information_page.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.finalprodproject.databinding.StudyInformationFragmentBinding
import com.example.finalprodproject.feature.study_information_page.data.repository.StudyInformationRepository
import com.google.android.material.transition.MaterialSharedAxis

class StudyInformationFragment : Fragment() {

    private val args by navArgs<StudyInformationFragmentArgs>()
    private val argId by lazy { args.id }

    private var _binding: StudyInformationFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModelFactory by lazy {
        StudyInformationViewModelFactory(
            StudyInformationRepository(
                context = requireContext()
            )
        )
    }
    private val viewModel: StudyInformationViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(
            MaterialSharedAxis.Z,
            /* forward= */ true
        ).apply {
            duration = 500
        }
        returnTransition = MaterialSharedAxis(
            MaterialSharedAxis.Z,
            /* forward= */ false
        ).apply {
            duration = 500
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = StudyInformationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.studyInformationData.observe(viewLifecycleOwner) { course ->
            binding.points.text = course.points.toString()
            binding.category.text = course.category
            binding.theme.text = course.title
            binding.description.text = course.description
            binding.authorName.text = course.author.description
            binding.exit.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
            binding.enrol.setOnClickListener {
                viewModel.subscribe(course.id)
                binding.enrol.visibility = View.GONE
                findNavController().navigate(
                    StudyInformationFragmentDirections.actionStudyInformationFragmentToRoadmapFragment(
                        argId
                    )
                )
            }
        }

        viewModel.loadCourseById(argId)
    }

}
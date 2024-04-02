package com.example.finalprodproject.feature.course_end_page.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.finalprodproject.databinding.CourseEndBinding
import com.google.android.material.transition.MaterialSharedAxis

class CourseEndFragment : Fragment() {

    private val args by navArgs<CourseEndFragmentArgs>()
    private val argName by lazy { args.name }
    private val argPoints by lazy { args.points }

    private var _binding: CourseEndBinding? = null
    private val binding get() = _binding!!

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
        _binding = CourseEndBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.courseName.text = argName
        binding.pointsVal.text = argPoints.toString()
    }

}
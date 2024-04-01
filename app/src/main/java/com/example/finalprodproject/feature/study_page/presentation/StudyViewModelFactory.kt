package com.example.finalprodproject.feature.study_page.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalprodproject.feature.study_page.data.repository.StudyRepository

@Suppress("UNCHECKED_CAST")
class StudyViewModelFactory(
    private val studyRepository: StudyRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudyViewModel::class.java)) {
            return StudyViewModel(
                studyRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
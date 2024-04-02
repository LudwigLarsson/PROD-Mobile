package com.example.finalprodproject.feature.study_information_page.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalprodproject.feature.study_information_page.data.repository.StudyInformationRepository

@Suppress("UNCHECKED_CAST")
class StudyInformationViewModelFactory(
    private val studyInformationRepository: StudyInformationRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudyInformationViewModel::class.java)) {
            return StudyInformationViewModel(
                studyInformationRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
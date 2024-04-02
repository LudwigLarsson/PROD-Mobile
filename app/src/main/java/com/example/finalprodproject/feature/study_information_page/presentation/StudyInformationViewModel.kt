package com.example.finalprodproject.feature.study_information_page.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprodproject.common.core.dto.Course
import com.example.finalprodproject.feature.study_information_page.data.repository.StudyInformationRepository
import kotlinx.coroutines.launch

class StudyInformationViewModel(
    private val studyInformationRepository: StudyInformationRepository
) : ViewModel() {

    private val _studyInformationData = MutableLiveData<Course>()
    val studyInformationData: LiveData<Course>
        get() = _studyInformationData

    fun loadCourseById(id: Int) {
        viewModelScope.launch {
            _studyInformationData.value = studyInformationRepository.getCourseById(id)
        }
    }

    fun subscribe(id: Int) {
        viewModelScope.launch {
            studyInformationRepository.subscribe(id)
        }
    }

}
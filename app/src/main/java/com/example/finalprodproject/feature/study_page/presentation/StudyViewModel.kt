package com.example.finalprodproject.feature.study_page.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprodproject.common.core.dto.Course
import com.example.finalprodproject.feature.study_page.data.repository.StudyRepository
import kotlinx.coroutines.launch

class StudyViewModel(
    private val studyRepository: StudyRepository
) : ViewModel() {

    private val _allCoursesData = MutableLiveData<List<Course>>()
    val allCoursesData: LiveData<List<Course>>
        get() = _allCoursesData

    private val _myCoursesData = MutableLiveData<List<Course>>()
    val myCoursesData: LiveData<List<Course>>
        get() = _myCoursesData

    fun loadAllCourses() {
        viewModelScope.launch {
            _allCoursesData.value = studyRepository.getAllCourses()
        }
    }

    fun loadMyCourses() {
        viewModelScope.launch {
            _myCoursesData.value = studyRepository.getMyCourses()
        }
    }

    fun clearAllCourses() {
        viewModelScope.launch {
            _allCoursesData.value = emptyList()
        }
    }

    fun clearMyCourses() {
        viewModelScope.launch {
            _myCoursesData.value = emptyList()
        }
    }

}
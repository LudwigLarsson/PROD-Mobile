package com.example.finalprodproject.feature.study_page.data.repository

import android.content.Context
import com.example.finalprodproject.common.core.dto.Course
import com.example.finalprodproject.common.core.dto.Profile
import com.example.finalprodproject.common.network.retrofit.RetrofitClient
import com.example.finalprodproject.feature_user.domain.helpers.UserStorageHandler

class StudyRepository(context: Context) {

    private val token by lazy {
        UserStorageHandler(context).token
    }

    suspend fun getAllCourses(): List<Course> {
        return RetrofitClient.apiCourses.getAllCourses(
            authHeader = "Bearer $token"
        )
    }

    suspend fun getMyCourses(): List<Course> {
        return RetrofitClient.apiCourses.getMyCourses(
            authHeader = "Bearer $token"
        )
    }

    suspend fun getProfile(): Profile {
        return RetrofitClient.apiCourses.getProfile(
            authHeader = "Bearer $token"
        )
    }

}
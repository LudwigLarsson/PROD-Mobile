package com.example.finalprodproject.feature.study_information_page.data.repository

import android.content.Context
import com.example.finalprodproject.common.core.dto.Course
import com.example.finalprodproject.common.network.retrofit.RetrofitClient
import com.example.finalprodproject.feature_user.domain.helpers.UserStorageHandler

class StudyInformationRepository(context: Context) {

    private val token by lazy {
        UserStorageHandler(context).token
    }

    suspend fun getCourseById(id: Int): Course {
        return RetrofitClient.apiCourseInformation.getCourseById(
            authHeader = "Bearer $token",
            id = id
        )
    }

}
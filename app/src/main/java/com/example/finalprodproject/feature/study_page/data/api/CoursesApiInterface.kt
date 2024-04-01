package com.example.finalprodproject.feature.study_page.data.api

import com.example.finalprodproject.common.core.dto.Course
import retrofit2.http.GET
import retrofit2.http.Header

interface CoursesApiInterface {

    @GET("themes/")
    suspend fun getAllCourses(
        @Header("Authorization") authHeader: String
    ): List<Course>

    @GET("themes/me")
    suspend fun getMyCourses(
        @Header("Authorization") authHeader: String
    ): List<Course>

}
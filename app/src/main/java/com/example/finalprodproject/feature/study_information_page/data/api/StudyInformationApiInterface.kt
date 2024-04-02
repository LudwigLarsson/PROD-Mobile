package com.example.finalprodproject.feature.study_information_page.data.api

import com.example.finalprodproject.common.core.dto.Course
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface StudyInformationApiInterface {

    @GET("themes/getBy/{id}")
    suspend fun getCourseById(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): Course

    @POST("themes/add/{id}")
    suspend fun subscribe(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): Course

}
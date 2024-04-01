package com.example.finalprodproject.common.network.retrofit

import com.example.finalprodproject.feature.study_information_page.data.api.StudyInformationApiInterface
import com.example.finalprodproject.feature.study_page.data.api.CoursesApiInterface
import com.example.finalprodproject.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private fun <T> create(baseUrl: String, service: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
                    )
                    .build()
            )
            .build()
            .create(service)
    }

    val apiCourses: CoursesApiInterface by lazy {
        create(Constants.USER_API_PATH, CoursesApiInterface::class.java)
    }

    val apiCourseInformation: StudyInformationApiInterface by lazy {
        create(Constants.USER_API_PATH, StudyInformationApiInterface::class.java)
    }

}
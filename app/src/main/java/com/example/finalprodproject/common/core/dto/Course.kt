package com.example.finalprodproject.common.core.dto

data class Course(
    val id: Int,
    val title: String,
    val category: String,
    val author: String,
    val points: Int,
    val students: Int,
    val graduates: Int
)
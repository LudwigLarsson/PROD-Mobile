package com.example.finalprodproject.common.core.dto

import java.io.Serializable

data class Course(
    val id: Int,
    val title: String,
    val category: String,
    val description: String,
    val explored: Boolean,
    val started: Boolean,
    val author: String,
    val points: Int,
    val students: Int,
    val graduates: Int,
    val underThemeIds: List<Int>
) : Serializable
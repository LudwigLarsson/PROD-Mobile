package com.example.finalprodproject.common.core.dto

data class Course(
    val id: Int,
    val title: String,
    val category: String,
    val description: String,
    val explored: Boolean,
    val started: Boolean,
    val author: Author,
    val under: List<UnderTheme>,
    val points: Int,
    val students: Int,
    val graduates: Int
) {
    data class Author(
        val id: Int,
        val userId: Int,
        val name: String,
        val description: String
    )
    data class UnderTheme(
        val title: String,
        val description: String,
        val videoUrl: String,
        val explored: Boolean,
        val started: Boolean,
        val image: String,
        val grade: Int,
        val points: Int,
        val tasks: List<Task>
    ) {
        data class Task(
            val description: String,
            val response: String,
            val explored: Boolean,
            val started: Boolean,
            val image: String
        )
    }
}
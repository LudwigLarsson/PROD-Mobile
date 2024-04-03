package com.example.finalprodproject.common.core.dto

data class Profile(
    val phone: String,
    val firstname: String,
    val surname: String?,
    val lastname: String?,
    val password: String?,
    val image: String?,
    val points: Int,
    val role: String,
    val achievement: List<Achievement>,
    val themes: List<Course>,
    val completeThemeIds: List<Int>,
    val completeUnderThemeIds: List<Int>,
    val completeTasksIds: List<Int>,
    val startedThemeIds: List<Int>,
    val startedUnderThemeIds: List<Int>,
    val startedTaskIds: List<Int>,
    val productIds: List<Int>
) {
    data class Achievement(
        val id: Int,
        val name: String,
        val description: String,
        val isCompleted: Boolean,
        val image: String?
    )
}

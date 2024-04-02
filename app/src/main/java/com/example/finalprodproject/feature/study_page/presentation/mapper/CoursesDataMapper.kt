package com.example.finalprodproject.feature.study_page.presentation.mapper

import com.example.finalprodproject.common.core.dto.Course
import com.example.finalprodproject.common.coreui.cources_category_item.CoursesCategoryItemViewModel
import com.example.finalprodproject.common.coreui.courses_item.CourseItemViewModel

object CoursesDataMapper {

    fun List<Course>.mapToViewModelsList() = map { it.mapToViewModel() }

    fun Course.mapToViewModel() = CourseItemViewModel(
        id = id,
        title = title,
        author = author.name,
        points = points,
        students = students,
        graduates = graduates,
        started = started,
        explored = explored
    )

    fun List<Course>.mapToViewModelByCategories(): List<CoursesCategoryItemViewModel> {
        return this
            .map { it.category }
            .toSet()
            .map { category -> 
                CoursesCategoryItemViewModel(
                    title = category,
                    filter { course ->
                        course.category == category
                    }.mapToViewModelsList())
            }
    }

}
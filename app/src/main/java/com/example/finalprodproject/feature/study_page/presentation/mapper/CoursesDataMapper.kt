package com.example.finalprodproject.feature.study_page.presentation.mapper

import com.example.finalprodproject.common.core.dto.Course
import com.example.finalprodproject.common.coreui.cources_category_item.CoursesCategoryItemViewModel
import com.example.finalprodproject.common.coreui.courses_item.CourseItemViewModel

object CoursesDataMapper {

    fun List<Course>.mapToViewModel() = map { course ->
        CourseItemViewModel(
            id = course.id,
            title = course.title,
            author = course.author,
            points = course.points,
            students = course.students,
            graduates = course.graduates
        )
    }

    fun Course.mapToViewModel() = CourseItemViewModel(
        id = id,
        title = title,
        author = author,
        points = points,
        students = students,
        graduates = graduates
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
                    }.mapToViewModel())
            }
    }

}
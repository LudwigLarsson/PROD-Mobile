package com.example.finalprodproject.common.coreui.cources_category_item

import com.example.finalprodproject.common.coreui.courses_item.CourseItemViewModel
import com.example.finalprodproject.utils.adapter.DelegateAdapterItem

class CoursesCategoryItemViewModel(
    val title: String,
    val courses: List<CourseItemViewModel>
) : DelegateAdapterItem {

    override fun id(): Any = title

    override fun content(): Any = courses

    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is CoursesCategoryItemViewModel) {
            if (courses != other.courses) {
                return ChangePayload.CoursesCategoryCoursesChanged(other.courses)
            }
        }
        return DelegateAdapterItem.Payloadable.None
    }

    sealed class ChangePayload: DelegateAdapterItem.Payloadable {
        data class CoursesCategoryCoursesChanged(val courses: List<CourseItemViewModel>): ChangePayload()
    }

}
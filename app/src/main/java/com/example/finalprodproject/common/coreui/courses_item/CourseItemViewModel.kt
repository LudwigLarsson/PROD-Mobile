package com.example.finalprodproject.common.coreui.courses_item

import com.example.finalprodproject.utils.adapter.DelegateAdapterItem

data class CourseItemViewModel(
    val id: Int,
    val title: String,
    val author: String,
    val points: Int,
    val students: Int,
    val graduates: Int,
) : DelegateAdapterItem {

    override fun id(): Any = id

    override fun content(): Any = title

    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is CourseItemViewModel) {
            if (title != other.title) {
                return ChangePayload.CourseTitleChanged(other.title)
            }
            if (author != other.author) {
                return ChangePayload.CourseAuthorChanged(other.author)
            }
            if (points != other.points) {
                return ChangePayload.CoursePointsChanged(other.points)
            }
            if (students != other.students) {
                return ChangePayload.CourseStudentsChanged(other.students)
            }
            if (graduates != other.graduates) {
                return ChangePayload.CourseGraduatesChanged(other.graduates)
            }
        }
        return DelegateAdapterItem.Payloadable.None
    }

    sealed class ChangePayload: DelegateAdapterItem.Payloadable {
        data class CourseTitleChanged(val title: String): ChangePayload()
        data class CourseAuthorChanged(val author: String): ChangePayload()
        data class CoursePointsChanged(val points: Int): ChangePayload()
        data class CourseStudentsChanged(val students: Int): ChangePayload()
        data class CourseGraduatesChanged(val graduates: Int): ChangePayload()
    }

}
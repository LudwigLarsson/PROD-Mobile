package com.example.finalprodproject.common.coreui.courses_item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprodproject.R
import com.example.finalprodproject.utils.adapter.DelegateAdapter
import com.example.finalprodproject.utils.adapter.DelegateAdapterItem

class CourseItemDelegateAdapter
    : DelegateAdapter<CourseItemViewModel, CourseItemDelegateAdapter.CourseItemViewHolder>(
    CourseItemViewModel::class.java
) {

    inner class CourseItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pointsTextView: TextView = itemView.findViewById(R.id.courses_item_points)
        private val authorTextView: TextView = itemView.findViewById(R.id.courses_item_author)
        private val titleTextView: TextView = itemView.findViewById(R.id.courses_item_title)
        private val studentsTextView: TextView = itemView.findViewById(R.id.courses_item_students_count)
        private val graduatesTextView: TextView = itemView.findViewById(R.id.courses_item_graduates_count)

        fun bindView(model: CourseItemViewModel) {
            bindPoints(model.points)
            bindAuthor(model.author)
            bindTitle(model.title)
            bindStudents(model.students)
            bindGraduates(model.graduates)
        }

        fun bindPoints(points: Int) {
            pointsTextView.text = points.toString()
        }

        fun bindAuthor(author: String) {
            authorTextView.text = author
        }

        fun bindTitle(title: String) {
            titleTextView.text = title
        }

        fun bindStudents(students: Int) {
            studentsTextView.text = students.toString()
        }

        fun bindGraduates(graduates: Int) {
            graduatesTextView.text = graduates.toString()
        }

    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = CourseItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.courses_item, parent, false)
    )

    override fun bindViewHolder(
        model: CourseItemViewModel,
        viewHolder: CourseItemDelegateAdapter.CourseItemViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        when (val payload = payloads.firstOrNull() as? CourseItemViewModel.ChangePayload) {
            is CourseItemViewModel.ChangePayload.CoursePointsChanged -> viewHolder.bindPoints(
                payload.points
            )
            is CourseItemViewModel.ChangePayload.CourseAuthorChanged -> viewHolder.bindAuthor(
                payload.author
            )
            is CourseItemViewModel.ChangePayload.CourseTitleChanged -> viewHolder.bindTitle(
                payload.title
            )
            is CourseItemViewModel.ChangePayload.CourseStudentsChanged -> viewHolder.bindStudents(
                payload.students
            )
            is CourseItemViewModel.ChangePayload.CourseGraduatesChanged -> viewHolder.bindGraduates(
                payload.graduates
            )
            else -> viewHolder.bindView(model)
        }
    }

}
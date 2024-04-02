package com.example.finalprodproject.common.coreui.cources_category_item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprodproject.R
import com.example.finalprodproject.common.coreui.courses_item.CourseItemDelegateAdapter
import com.example.finalprodproject.common.coreui.courses_item.CourseItemViewModel
import com.example.finalprodproject.utils.adapter.CompositeAdapter
import com.example.finalprodproject.utils.adapter.DelegateAdapter
import com.example.finalprodproject.utils.adapter.DelegateAdapterItem

class CoursesCategoryItemDelegateAdapter(
    private val action: (View, CourseItemViewModel) -> Unit
)
    : DelegateAdapter<CoursesCategoryItemViewModel, CoursesCategoryItemDelegateAdapter.CoursesCategoryItemViewHolder>(
    CoursesCategoryItemViewModel::class.java
) {

    inner class CoursesCategoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.courses_category_item_title)
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.courses_category_item_recycler)

        fun bindView(model: CoursesCategoryItemViewModel) {
            bindTitle(model.title)
            bindAdapter()
            bindCourses(model.courses)
        }

        private fun bindAdapter() {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context).apply { setOrientation(
                    LinearLayoutManager.HORIZONTAL
                ) }
                adapter = CompositeAdapter.Builder()
                    .add(CourseItemDelegateAdapter(action))
                    .build()
            }
        }

        private fun bindTitle(title: String) {
            titleTextView.text = title
        }

        fun bindCourses(courses: List<CourseItemViewModel>) {
            (recyclerView.adapter as CompositeAdapter).submitList(courses)
        }

    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = CoursesCategoryItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.courses_category_item, parent, false)
    )

    override fun bindViewHolder(
        model: CoursesCategoryItemViewModel,
        viewHolder: CoursesCategoryItemDelegateAdapter.CoursesCategoryItemViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        when (val payload = payloads.firstOrNull() as? CoursesCategoryItemViewModel.ChangePayload) {
            is CoursesCategoryItemViewModel.ChangePayload.CoursesCategoryCoursesChanged -> viewHolder.bindCourses(
                payload.courses
            )
            else -> viewHolder.bindView(model)
        }
    }

}
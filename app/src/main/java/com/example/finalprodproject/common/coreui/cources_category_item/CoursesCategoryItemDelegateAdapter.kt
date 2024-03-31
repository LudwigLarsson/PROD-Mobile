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
import com.example.finalprodproject.utils.adapter.item_decoration.MarginHorizontalItemDecoration

class CoursesCategoryItemDelegateAdapter
    : DelegateAdapter<CoursesCategoryItemViewModel, CoursesCategoryItemDelegateAdapter.CoursesCategoryItemViewHolder>(
    CoursesCategoryItemViewModel::class.java
) {

    private val compositeAdapter by lazy {
        CompositeAdapter.Builder()
            .add(CourseItemDelegateAdapter())
            .build()
    }

    inner class CoursesCategoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.courses_category_item_title)
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.courses_category_item_recycler)

        fun bindView(model: CoursesCategoryItemViewModel) {
            bindAdapter()
            bindTitle(model.title)
            bindCourses(model.courses)
        }

        private fun bindAdapter() {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context).apply { setOrientation(
                    LinearLayoutManager.HORIZONTAL
                ) }
                addItemDecoration(MarginHorizontalItemDecoration(context.resources.getDimensionPixelSize(R.dimen.margin_big)))
                adapter = compositeAdapter
            }
        }

        private fun bindTitle(title: String) {
            titleTextView.text = title
        }

        fun bindCourses(courses: List<CourseItemViewModel>) {
            compositeAdapter.submitList(courses)
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
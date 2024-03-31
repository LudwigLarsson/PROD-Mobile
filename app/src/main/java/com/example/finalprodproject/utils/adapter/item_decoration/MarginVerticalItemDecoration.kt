package com.example.finalprodproject.utils.adapter.item_decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginVerticalItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            top = if (parent.getChildAdapterPosition(view) == 0) {
                0
            } else {
                spaceSize
            }
            bottom = spaceSize
        }
    }
}
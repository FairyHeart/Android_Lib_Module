package com.fairy.module.ktx

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/12/14.
 */
class SpaceItemDecoration(
    private val left: Int = -1,
    private val right: Int = -1,
    private val top: Int = -1,
    private val bottom: Int = -1
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (left >= 0) {
            outRect.left = left
        }
        if (right >= 0) {
            outRect.right = right
        }
        if (top >= 0) {
            outRect.top = top
        }
        if (bottom >= 0) {
            outRect.bottom = bottom
        }
    }
}
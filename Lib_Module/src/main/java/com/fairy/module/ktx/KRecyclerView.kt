package com.fairy.module.ktx

import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView拓展函数
 *
 * @author: Fairy.
 * @date  : 2020/12/14.
 */
fun RecyclerView.addItemDecoration(@DimenRes right: Int, @DimenRes bottom: Int) {
    this.addItemDecoration(
        SpaceItemDecoration(
            right = resources.getDimensionPixelSize(right),
            bottom = resources.getDimensionPixelSize(bottom)
        )
    )
}
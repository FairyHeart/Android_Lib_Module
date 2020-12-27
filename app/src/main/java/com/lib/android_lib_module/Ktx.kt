package com.lib.android_lib_module

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/12/27.
 */
inline fun Context.inflateLayout(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup? = null,
    attachToRoot: Boolean = false
): View = LayoutInflater.from(this).inflate(layoutResId, parent, attachToRoot)

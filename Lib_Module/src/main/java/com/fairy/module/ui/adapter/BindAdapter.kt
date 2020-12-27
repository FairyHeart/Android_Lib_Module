package com.fairy.module.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * 基础Adapter
 *
 * @author: Fairy.
 * @date  : 2020/12/27.
 */
abstract class BindAdapter<T, VM : ViewDataBinding>(
    @LayoutRes val layoutId: Int
) : RecyclerView.Adapter<BindAdapter.MyViewHolder<VM>>() {

    lateinit var mContext: Context

    var mData: MutableList<T>? = null

    class MyViewHolder<VM : ViewDataBinding>(val binding: VM) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder<VM> {
        mContext = parent.context
        val binding =
            DataBindingUtil.inflate<VM>(LayoutInflater.from(mContext), layoutId, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder<VM>, position: Int) {
        val item = mData?.get(position) ?: return
        val binding = holder.binding
        this.onBindViewHolder(item, binding, position)
    }

    abstract fun onBindViewHolder(item: T, binding: VM, position: Int)

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    open fun clear() {
        mData?.clear()
        notifyDataSetChanged()
    }

    open fun setNewData(data: MutableList<T>?) {
        this.mData = data
        this.notifyDataSetChanged()
    }

    open fun addData(data: MutableList<T>?) {
        if (!data.isNullOrEmpty()) {
            if (this.mData == null) {
                this.mData = mutableListOf()
            }
            this.mData?.addAll(data)
        }
    }

    open fun getData(): MutableList<T>? {
        return mData
    }
}
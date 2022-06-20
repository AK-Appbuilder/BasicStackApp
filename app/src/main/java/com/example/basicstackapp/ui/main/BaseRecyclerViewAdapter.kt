package com.example.basicstackapp.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.example.basicstackapp.R

abstract class BaseRecyclerViewAdapter<T, R: RecyclerView.ViewHolder> : RecyclerView.Adapter<R>() {

    private val itemsList: MutableList<T> = mutableListOf()

    open fun submitList(list: List<T>) {
        itemsList.addAll(list)
        notifyDataSetChanged()
    }


    open fun replaceList(list: List<T>) {
        itemsList.clear()
        itemsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun getItem(position: Int): T {
//        if (position >= 0 && position < itemsList.size)
            return itemsList[position]
//        else return null
    }

    fun getItemsList(): List<T> {
        return itemsList
    }

    fun updateItemAt(index: Int, item: T) {
        if (index in 0 until itemCount) {
            itemsList[index] = item
            notifyItemChanged(index)
        }
    }
}
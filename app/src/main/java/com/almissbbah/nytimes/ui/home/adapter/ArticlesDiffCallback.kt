package com.almissbbah.nytimes.ui.home.adapter

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.almissbbah.nytimes.data.remote.model.Article

class ArticlesDiffCallback(
    private val oldList: MutableList<Article>,
    private val newList: List<Article>
) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].abstract == newList[newItemPosition].abstract
    }


    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return newList[newItemPosition]
    }

}
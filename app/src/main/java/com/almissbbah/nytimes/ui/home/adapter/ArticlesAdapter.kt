package com.almissbbah.nytimes.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.almissbbah.nytimes.R
import com.almissbbah.nytimes.data.remote.model.Article
import com.almissbbah.nytimes.databinding.ArticlesListItemBinding
import com.almissbbah.nytimes.utils.isValidUrl
import com.almissbbah.nytimes.utils.trimWithDots
import com.bumptech.glide.Glide


class ArticlesAdapter :
    RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    private var mList: MutableList<Article> = mutableListOf()
    var mItemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ArticleViewHolder {
        val context: Context = viewGroup.context
        val layoutInflater = LayoutInflater.from(context)
        val binding: ArticlesListItemBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.articles_list_item,
                viewGroup,
                false
            )

        return ArticleViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(
        holder: ArticleViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            val article = payloads[0] as Article
            updateItemView(holder, position, article)
        }
    }


    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article: Article = mList[holder.adapterPosition]
        updateItemView(holder, position, article)
    }

    fun setData(newArticles: List<Article>) {
        val diffCallback =
            ArticlesDiffCallback(
                mList,
                newArticles
            )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        mList = newArticles.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

    private fun updateItemView(holder: ArticleViewHolder, position: Int, article: Article) {
        //mList[position] = article

        holder.binding.article = article
        holder.binding.articleTitle.text = article.title.trimWithDots(60)
        if (article.media.isNotEmpty() && article.media.last().metaData.isNotEmpty()) {
            val url = article.media.last().metaData.last().url
            if (url.isValidUrl())
                Glide
                    .with(holder.binding.root.context)
                    .load(article.media.last().metaData.last().url).fitCenter().circleCrop()
                    .into(holder.binding.ivArticleImage)
        }
        holder.binding.root.setOnClickListener {
            mItemClickListener?.onClicked(it, article, position)
        }

    }

    class ArticleViewHolder(itemBinding: ArticlesListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding: ArticlesListItemBinding = itemBinding
    }


    interface ItemClickListener {
        fun onClicked(view: View, article: Article, position: Int)
    }
}
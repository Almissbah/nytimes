package com.almissbbah.nytimes.ui.details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.almissbbah.nytimes.data.remote.model.Article
import com.almissbbah.nytimes.data.remote.model.ArticleDetails
import com.almissbbah.nytimes.utils.RxArticleHolder
import com.almissbbah.nytimes.utils.getDetails
import com.almissbbah.nytimes.utils.openUrlInBrowser
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ArticleDetailsViewModel @Inject constructor() : ViewModel() {
    private var disposable: Disposable? = null
    lateinit var mArticle: Article
    private val _articleDetails = MutableLiveData<ArticleDetails>()
    val articleDetails: LiveData<ArticleDetails> = _articleDetails

    fun subscribe() {
        disposable = RxArticleHolder.article.subscribe {
            _articleDetails.postValue(it.getDetails())
            mArticle = it
        }

    }

    fun unSubscribe() {
        disposable?.dispose()
    }

    fun openUrlInBrowser(context: Context) {
        mArticle.url.openUrlInBrowser(context)
    }
}
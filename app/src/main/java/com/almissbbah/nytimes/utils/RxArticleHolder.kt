package com.almissbbah.nytimes.utils

import com.almissbbah.nytimes.data.remote.model.Article
import io.reactivex.subjects.BehaviorSubject

object RxArticleHolder {

    val article: BehaviorSubject<Article> = BehaviorSubject.create()

    fun selectArticle(newArticle: Article) {
        article.onNext(newArticle)
    }
}
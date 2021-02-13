package com.almissbbah.nytimes.data.repo

import com.almissbbah.nytimes.data.remote.model.PopularArticlesApiResponse
import com.almissbbah.nytimes.data.remote.model.PopularArticlesRequest
import io.reactivex.disposables.Disposable

interface PopularArticlesRepository {
    fun getPopularArticles(
        request: PopularArticlesRequest,
        callback: RepoCallback<PopularArticlesApiResponse>
    ): Disposable
}
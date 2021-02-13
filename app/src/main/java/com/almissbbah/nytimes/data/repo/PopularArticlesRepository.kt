package com.almissbbah.nytimes.data.repo

import com.almissbbah.nytimes.data.remote.model.PopularArticlesApiResponse
import com.almissbbah.nytimes.data.remote.model.PopularArticlesRequest
import io.reactivex.Observable
import retrofit2.Response

interface PopularArticlesRepository {
    fun getPopularArticles(request: PopularArticlesRequest): Observable<Response<PopularArticlesApiResponse>>
}
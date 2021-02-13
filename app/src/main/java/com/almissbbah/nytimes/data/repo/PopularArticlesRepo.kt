package com.almissbbah.nytimes.data.repo

import com.almissbbah.nytimes.data.remote.PopularArticlesApiService
import com.almissbbah.nytimes.data.remote.model.PopularArticlesApiResponse
import com.almissbbah.nytimes.data.remote.model.PopularArticlesRequest
import io.reactivex.Observable
import retrofit2.Response

class PopularArticlesRepo(
    private val popularArticlesApiService: PopularArticlesApiService
) : PopularArticlesRepository {

    override fun getPopularArticles(request: PopularArticlesRequest): Observable<Response<PopularArticlesApiResponse>> {
        return popularArticlesApiService.getArticles(request.section, request.period.value)
    }

}
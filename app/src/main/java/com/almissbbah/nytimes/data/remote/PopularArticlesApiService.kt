package com.almissbbah.nytimes.data.remote

import com.almissbbah.nytimes.data.remote.model.PopularArticlesApiResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PopularArticlesApiService {

    @GET("/svc/mostpopular/v2/mostviewed/{section}/{period}.json")
    fun getArticles(
        @Path(value = "section") section: String,
        @Path(value = "period") period: Int
    ): Observable<Response<PopularArticlesApiResponse>>
}
package com.almissbbah.nytimes.data.remote.interceptor

import com.almissbbah.nytimes.utils.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newURL = request.url().newBuilder().addQueryParameter("api-key", API_KEY).build()
        val modifiedRequest = request.newBuilder().url(newURL)

        return chain.proceed(modifiedRequest.build())
    }
}
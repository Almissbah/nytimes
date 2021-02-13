package com.almissbbah.nytimes.data.remote

import com.almissbbah.nytimes.data.remote.model.ErrorBody
import com.google.gson.Gson
import io.reactivex.observers.DisposableObserver
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection.*
import java.net.SocketTimeoutException

open class CallbackWrapper<T>(private val callback: HttpCallback<T>) :
    DisposableObserver<Response<T>>() {
    lateinit var errorBody: ErrorBody

    private fun onSuccess(t: Response<T>) {

        when {
            t.code() == HTTP_OK -> {
                callback.onSuccess(t.body())
            }
            t.code() == HTTP_CREATED -> {
                callback.onCreated(t.body())
            }
            t.code() == HTTP_FORBIDDEN || t.code() == HTTP_UNAUTHORIZED -> {
                errorBody =
                    Gson().fromJson<ErrorBody>(
                        t.errorBody()?.string() ?: "{}",
                        ErrorBody::class.java
                    )
                callback.onForbidden(errorBody)
            }
            t.code() == HTTP_NOT_FOUND -> {
                errorBody =
                    Gson().fromJson<ErrorBody>(
                        t.errorBody()?.string() ?: "{}",
                        ErrorBody::class.java
                    )
                callback.onNotFound(errorBody)
            }
            t.code() >= HTTP_BAD_REQUEST -> {
                errorBody =
                    Gson().fromJson<ErrorBody>(
                        t.errorBody()?.string() ?: "{}",
                        ErrorBody::class.java
                    )
                callback.onServerError(errorBody)
            }
        }
    }

    override fun onNext(t: Response<T>) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        when (e) {
            is SocketTimeoutException -> {
                callback.onNetworkError()
            }
            is IOException -> {
                callback.onNetworkError()
            }
            else -> {
                callback.onNetworkError()
            }
        }
    }

    override fun onComplete() {}

}


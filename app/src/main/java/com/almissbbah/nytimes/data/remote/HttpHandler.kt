package com.almissbbah.nytimes.data.remote

import com.almissbbah.nytimes.data.Resource
import com.almissbbah.nytimes.data.remote.model.ErrorBody
import com.almissbbah.nytimes.data.repo.RepoCallback


interface HttpCallback<T> {
    fun onCreated(t: T?)
    fun onSuccess(t: T?)
    fun onNetworkError()
    fun onNotFound(errorBody: ErrorBody)
    fun onServerError(errorBody: ErrorBody)
    fun onFail(t: T?, msg: String)
    fun onForbidden(errorBody: ErrorBody)
}

abstract class HttpHandler<T>(val callback: RepoCallback<T>) : HttpCallback<T> {
    override fun onCreated(t: T?) {
        callback.onResult(Resource(t, Resource.Status.FORBIDDEN, ""))
    }

    override fun onServerError(errorBody: ErrorBody) {
        callback.onResult(Resource(null, Resource.Status.SERVER_ERROR, errorBody.message))
    }

    override fun onFail(t: T?, msg: String) {
        callback.onResult(Resource(null, Resource.Status.FAIL, ""))
    }

    override fun onForbidden(errorBody: ErrorBody) {
        callback.onResult(Resource(null, Resource.Status.FORBIDDEN, errorBody.message))
    }

    override fun onNetworkError() {
        callback.onResult(Resource(null, Resource.Status.CONNECTION_ERROR, ""))
    }

    override fun onNotFound(errorBody: ErrorBody) {
        callback.onResult(Resource(null, Resource.Status.NOT_FOUND, errorBody.message))
    }
}
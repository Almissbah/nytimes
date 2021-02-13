package com.almissbbah.nytimes.data.repo

import com.almissbbah.nytimes.data.Resource
import com.almissbbah.nytimes.data.remote.CallbackWrapper
import com.almissbbah.nytimes.data.remote.HttpHandler
import com.almissbbah.nytimes.data.remote.PopularArticlesApiService
import com.almissbbah.nytimes.data.remote.model.PopularArticlesApiResponse
import com.almissbbah.nytimes.data.remote.model.PopularArticlesRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PopularArticlesRepo(
    private val popularArticlesApiService: PopularArticlesApiService
) : PopularArticlesRepository {

    override fun getPopularArticles(
        request: PopularArticlesRequest,
        callback: RepoCallback<PopularArticlesApiResponse>
    ): Disposable {

        val callbackWrapper = getCallbackWrapper(callback)
        popularArticlesApiService.getArticles(request.section, request.period.value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callbackWrapper)

        return callbackWrapper

    }

    private fun getCallbackWrapper(callback: RepoCallback<PopularArticlesApiResponse>): CallbackWrapper<PopularArticlesApiResponse> {
        return CallbackWrapper(object :
            HttpHandler<PopularArticlesApiResponse>(callback) {
            override fun onSuccess(t: PopularArticlesApiResponse?) {
                callback.onResult(Resource(t, Resource.Status.SUCCESS, ""))
            }
        })
    }

}
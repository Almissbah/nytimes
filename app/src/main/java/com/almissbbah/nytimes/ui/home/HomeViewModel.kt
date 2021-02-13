package com.almissbbah.nytimes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.almissbbah.nytimes.data.Resource
import com.almissbbah.nytimes.data.remote.model.Article
import com.almissbbah.nytimes.data.remote.model.ArticlesPeriod
import com.almissbbah.nytimes.data.remote.model.PopularArticlesApiResponse
import com.almissbbah.nytimes.data.remote.model.PopularArticlesRequest
import com.almissbbah.nytimes.data.repo.PopularArticlesRepository
import com.almissbbah.nytimes.data.repo.RepoCallback
import com.almissbbah.nytimes.utils.Log
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val popularArticlesRepository: PopularArticlesRepository) :
    ViewModel() {
    private var mLatestRequest: PopularArticlesRequest? = null
    val tag = "HomeViewModel"

    enum class Action { SHOW_LOADING, SHOW_ARTICLES_LIST, SHOW_CONNECTION_ERROR, SHOW_AUTH_ERROR }

    private var mDisposable: Disposable? = null
    private val _articles = MutableLiveData<Resource<List<Article>, Action>>()
    val articles: LiveData<Resource<List<Article>, Action>> = _articles


    fun getPopularArticlesForLast7Days() {
        getPopularArticles(PopularArticlesRequest(period = ArticlesPeriod.SEVEN_DAYS))
    }

    fun getPopularArticles(request: PopularArticlesRequest) {
        mLatestRequest = request
        showLoading()
        mDisposable = popularArticlesRepository.getPopularArticles(
            request,
            object : RepoCallback<PopularArticlesApiResponse> {
                override fun onResult(result: Resource<PopularArticlesApiResponse, Resource.Status>) {
                    when (result.action) {
                        Resource.Status.SUCCESS -> {
                            Log.d(
                                tag,
                                "Resource.Status.SUCCESS : list size =${result.payload!!.articles.size}"
                            )
                            _articles.postValue(
                                Resource(
                                    result.payload.articles,
                                    Action.SHOW_ARTICLES_LIST,
                                    ""
                                )
                            )
                        }
                        Resource.Status.CONNECTION_ERROR, Resource.Status.FAIL,
                        Resource.Status.SERVER_ERROR, Resource.Status.NOT_FOUND -> {
                            Log.e(tag, "CONNECTION_ERROR")
                            _articles.postValue(
                                Resource(
                                    null,
                                    Action.SHOW_CONNECTION_ERROR,
                                    "Connection failed, Please check your internet and try again."
                                )
                            )
                        }
                        Resource.Status.FORBIDDEN -> {
                            Log.e(tag, "FORBIDDEN")
                            _articles.postValue(
                                Resource(
                                    null,
                                    Action.SHOW_AUTH_ERROR,
                                    "Authentication failed."
                                )
                            )
                        }
                    }
                }
            })
    }

    private fun showLoading() {
        Log.d(tag, "Showing loading")
        _articles.postValue(
            Resource(
                null,
                Action.SHOW_LOADING,
                ""
            )
        )
    }

    fun unSubscribe() {
        mDisposable?.dispose()
    }

    fun retry() {
        getPopularArticles(mLatestRequest ?: PopularArticlesRequest())
    }


}
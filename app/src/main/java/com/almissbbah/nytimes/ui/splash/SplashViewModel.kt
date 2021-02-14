package com.almissbbah.nytimes.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.almissbbah.nytimes.data.Resource
import com.almissbbah.nytimes.utils.SPLASH_SCREEN_DELAY_MS
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {
    enum class Action { NAVIGATE_TO_HOME_SCREEN }

    private var disposable: Disposable? = null
    private val _action = MutableLiveData<Resource<Any, Action>>()
    val action: LiveData<Resource<Any, Action>> = _action

    fun startTimer() {
        showHomeScreenAfterDelay()
    }

    private fun showHomeScreenAfterDelay() {
        disposable = Observable.timer(SPLASH_SCREEN_DELAY_MS, TimeUnit.MILLISECONDS)
            .map {
                _action.postValue(Resource(null, Action.NAVIGATE_TO_HOME_SCREEN, ""))
            }.observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    fun unSubscribe() {
        disposable?.dispose()
    }
}
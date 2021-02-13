package com.almissbbah.nytimes.ui.splash

import androidx.lifecycle.ViewModelProvider
import com.almissbbah.nytimes.R
import com.almissbbah.nytimes.ui.AppBaseFragment
import javax.inject.Inject

class SplashFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private lateinit var splashViewModel: SplashViewModel

    override fun initViewModel() {

    }

    override fun initViews() {
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
    }

    override fun onCreateView(): Int {
        return R.layout.fragment_slideshow
    }


}
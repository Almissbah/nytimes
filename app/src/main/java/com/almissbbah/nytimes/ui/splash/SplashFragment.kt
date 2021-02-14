package com.almissbbah.nytimes.ui.splash

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.almissbbah.nytimes.R
import com.almissbbah.nytimes.ui.AppBaseFragment
import javax.inject.Inject

class SplashFragment : AppBaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private lateinit var splashViewModel: SplashViewModel

    override fun initViewModel() {
        splashViewModel =
            ViewModelProviders.of(
                this,
                viewModelFactory
            )[SplashViewModel::class.java]
    }

    override fun initViews() {
    }

    override fun subscribe() {
        splashViewModel.action.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_nav_splash_to_nav_home)
        })
        splashViewModel.startTimer()
    }

    override fun unSubscribe() {
        splashViewModel.unSubscribe()
    }

    override fun onCreateView(): Int {
        return R.layout.fragment_splash
    }


}
package com.almissbbah.nytimes.ui.home


import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.almissbbah.nytimes.R
import com.almissbbah.nytimes.ui.AppBaseFragment
import javax.inject.Inject

class HomeFragment : AppBaseFragment() {


    companion object {
        const val TAG = "HomeFragment"
    }

    private lateinit var mViewModel: HomeViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun initViewModel() {
        mViewModel =
            ViewModelProviders.of(
                this,
                viewModelFactory
            )[HomeViewModel::class.java]
    }

    override fun initViews() {
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
    }

    override fun onCreateView(): Int {
        return R.layout.fragment_home
    }

}
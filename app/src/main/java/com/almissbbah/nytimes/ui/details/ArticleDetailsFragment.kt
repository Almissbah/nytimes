package com.almissbbah.nytimes.ui.details

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.almissbbah.nytimes.R
import com.almissbbah.nytimes.ui.AppBaseFragment
import javax.inject.Inject

class ArticleDetailsFragment : AppBaseFragment() {


    companion object {
        const val TAG = "ArticleDetailsFragment"
    }

    private lateinit var mViewModel: ArticleDetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun initViewModel() {
        mViewModel =
            ViewModelProviders.of(
                this,
                viewModelFactory
            )[ArticleDetailsViewModel::class.java]
    }

    override fun initViews() {
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
    }

    override fun onCreateView(): Int {
        return R.layout.fragment_article_details
    }


}
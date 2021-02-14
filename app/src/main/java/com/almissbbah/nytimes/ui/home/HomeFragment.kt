package com.almissbbah.nytimes.ui.home


import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.almissbbah.nytimes.R
import com.almissbbah.nytimes.data.Resource
import com.almissbbah.nytimes.data.remote.model.Article
import com.almissbbah.nytimes.ui.AppBaseFragment
import com.almissbbah.nytimes.ui.home.adapter.ArticlesAdapter
import com.almissbbah.nytimes.utils.hide
import com.almissbbah.nytimes.utils.unhide
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : AppBaseFragment() {


    companion object {
        const val TAG = "HomeFragment"
    }

    private lateinit var mViewModel: HomeViewModel

    private var mAdapter: ArticlesAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private val mObserver = Observer<Resource<List<Article>, HomeViewModel.Action>> { t ->
        when (t.action) {
            HomeViewModel.Action.SHOW_LOADING -> showLoading()
            HomeViewModel.Action.SHOW_ARTICLES_LIST -> updateArticlesList(t.payload)
            HomeViewModel.Action.SHOW_CONNECTION_ERROR -> showConnectionError()
            HomeViewModel.Action.SHOW_SERVER_ERROR -> showAuthError()
        }
    }

    override fun initViewModel() {
        mViewModel =
            ViewModelProviders.of(
                this,
                viewModelFactory
            )[HomeViewModel::class.java]
    }


    override fun onCreateView(): Int {
        return R.layout.fragment_home
    }


    override fun initViews() {
        updateToolBarTitle(getString(R.string.home_fragment_title))
        initRvArticles()
        btnRetry.setOnClickListener {
            mViewModel?.retry()
        }
    }

    private fun initRvArticles() {
        rvArticles.layoutManager = LinearLayoutManager(this.context)
        mAdapter = ArticlesAdapter()
        mAdapter!!.mItemClickListener = object : ArticlesAdapter.ItemClickListener {
            override fun onClicked(view: View, article: Article, position: Int) {
                Log.i(tag, "User Selected Article with title: ${article.title}")
                mViewModel.selectArticle(article)
                findNavController().navigate(R.id.action_nav_home_to_nav_details)
            }
        }
        rvArticles.adapter = mAdapter

    }

    private fun showAuthError() {
        tvError.text = getString(R.string.auth_failed_msg)
        tvError.unhide()
        btnRetry.unhide()
        rvArticles.hide()
        hideLoading()
    }

    private fun showConnectionError() {
        tvError.text = getString(R.string.connection_failed_msg)
        hideLoading()
        tvError.unhide()
        btnRetry.unhide()
        rvArticles.hide()
    }

    private fun updateArticlesList(articles: List<Article>?) {
        hideLoading()
        hideErrorLayout()
        rvArticles.unhide()
        mAdapter?.setData(articles ?: listOf())
    }

    private fun hideErrorLayout() {
        tvError.hide()
        btnRetry.hide()
    }

    override fun subscribe() {
        mViewModel.articles.observe(
            this,
            mObserver
        )
        mViewModel.getPopularArticlesForLast7Days()
    }

    override fun unSubscribe() {
        mViewModel.unSubscribe()
    }

    private fun showLoading() {
        hideErrorLayout()
        rvArticles.hide()
        progressBar.unhide()
    }

    private fun hideLoading() {
        progressBar.hide()
    }
}
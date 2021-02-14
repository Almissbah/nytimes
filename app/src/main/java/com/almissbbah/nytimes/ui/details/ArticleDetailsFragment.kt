package com.almissbbah.nytimes.ui.details

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.almissbbah.nytimes.R
import com.almissbbah.nytimes.data.remote.model.ArticleDetails
import com.almissbbah.nytimes.data.remote.model.MediaMetaData
import com.almissbbah.nytimes.ui.AppBaseFragment
import com.almissbbah.nytimes.utils.Log
import com.almissbbah.nytimes.utils.isValidUrl
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_article_details.*
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
        updateToolBarTitle(getString(R.string.article_details_title))
        btnReadFull.setOnClickListener {
            mViewModel.openUrlInBrowser(requireContext())
        }
    }

    override fun subscribe() {
        mViewModel.articleDetails.observe(this, Observer {
            updateUI(it)
        })
        mViewModel.subscribe()
    }

    private fun updateUI(it: ArticleDetails) {
        with(it) {
            tvKeywords.text = keywords
            tvSection.text = "${section} ${subSection}"
            tvSource.text = source
            tvByline.text = byline
            articleTitle.text = title
            articleAbstract.text = abstract
            publishDate.text = publishedDate
            setArticleImage(mediaMetadata)
        }
    }

    private fun setArticleImage(mediaMetadata: MediaMetaData?) {
        if (mediaMetadata != null && (mediaMetadata.url.isValidUrl())) {
            Glide
                .with(this)
                .load(mediaMetadata.url).fitCenter()
                .into(articleImage)
        } else {
            Log.e(tag, "${mediaMetadata?.url} is not a valid Image Url.")
        }
    }

    override fun unSubscribe() {
        mViewModel.unSubscribe()
    }

    override fun onCreateView(): Int {
        return R.layout.fragment_article_details
    }


}
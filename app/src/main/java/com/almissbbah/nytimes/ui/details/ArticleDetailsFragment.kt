package com.almissbbah.nytimes.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.almissbbah.nytimes.R

class ArticleDetailsFragment : Fragment() {

    private lateinit var articleDetailsViewModel: ArticleDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        articleDetailsViewModel =
            ViewModelProviders.of(this).get(ArticleDetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_article_details, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        articleDetailsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
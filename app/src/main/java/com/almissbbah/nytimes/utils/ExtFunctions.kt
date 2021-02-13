package com.almissbbah.nytimes.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import com.almissbbah.nytimes.data.remote.model.Article
import com.almissbbah.nytimes.data.remote.model.ArticleDetails
import com.almissbbah.nytimes.data.remote.model.MediaMetaData

fun View.hide() {
    this.visibility = View.GONE
}

fun View.unhide() {
    this.visibility = View.VISIBLE
}


fun Article.getDetails(): ArticleDetails {
    var mediaMetaData: MediaMetaData? = null

    if (this.media.isNotEmpty() && this.media.last().metaData.isNotEmpty()) {
        mediaMetaData = this.media.last().metaData.last()
    }
    return ArticleDetails(
        this.title,
        this.url,
        this.byline,
        this.abstract,
        this.source,
        this.updatedAt,
        this.section,
        this.subSection,
        this.keywords, mediaMetaData, this.publishedDate
    )
}


fun String.isValidUrl(): Boolean {
    val regex = "^((h|H)ttps?:\\/\\/)(www.)?[a-zA-Z0-9-+#\$@\\/?_=&\\.,%]+"
    return Regex(regex).matches(this)
}

fun String.trimWithDots(length: Int): String {
    var newTitle = this
    if (this.length > length) {
        newTitle = this.substring(0, length) + "..."
    }
    return newTitle
}

fun String.openUrlInBrowser(context: Context) {
    if (this.isValidUrl()) {
        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(this))
        context.startActivity(browserIntent)
    }

}

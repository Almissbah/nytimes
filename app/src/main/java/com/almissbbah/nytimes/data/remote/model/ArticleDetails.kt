package com.almissbbah.nytimes.data.remote.model


data class ArticleDetails(
    val title: String,
    val url: String,
    val byline: String,
    val abstract: String,
    val source: String,
    val updatedAt: String,
    val section: String,
    val subSection: String,
    val keywords: String, val mediaMetadata: MediaMetaData?,
    val publishedDate: String
)
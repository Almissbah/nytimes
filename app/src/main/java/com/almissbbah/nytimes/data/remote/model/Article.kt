package com.almissbbah.nytimes.data.remote.model

import com.google.gson.annotations.SerializedName

data class Article(
    val title: String,
    val url: String,
    val byline: String,
    val abstract: String,
    val source: String,
    @SerializedName("updated") val updatedAt: String,
    val section: String,
    @SerializedName("subsection") val subSection: String,
    @SerializedName("adx_keywords") val keywords: String, val media: List<Media>,
    @SerializedName("published_date") val publishedDate: String
)
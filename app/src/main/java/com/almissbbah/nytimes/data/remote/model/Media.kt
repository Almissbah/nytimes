package com.almissbbah.nytimes.data.remote.model

import com.google.gson.annotations.SerializedName

data class Media(
    val caption: String,
    val type: String,
    @SerializedName("copyright") val copyRight: String,
    @SerializedName("subtype") val subType: String,
    @SerializedName("media-metadata") val metaData: List<MediaMetaData>

)
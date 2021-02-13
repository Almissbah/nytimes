package com.almissbbah.nytimes.data.remote.model

data class PopularArticlesRequest(
    val section: String = "all-sections",
    val period: ArticlesPeriod = ArticlesPeriod.SEVEN_DAYS
)

enum class ArticlesPeriod(val value: Int) { ONE_DAY(1), SEVEN_DAYS(7), THIRTY_DAYS(30) }

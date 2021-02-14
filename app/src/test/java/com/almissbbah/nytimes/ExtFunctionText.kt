package com.almissbbah.nytimes

import com.almissbbah.nytimes.data.remote.model.Article
import com.almissbbah.nytimes.utils.getDetails
import com.almissbbah.nytimes.utils.isValidUrl
import com.almissbbah.nytimes.utils.trimWithDots
import org.junit.Assert
import org.junit.Test

class ExtFunctionText {
    val url1 = ""
    val url2 = "http://google.com"
    val url3 = "https://google.com"
    val url4 = "http:/google.com"
    val url5 = "http//google.com"


    val article = Article(
        "SomeTile",
        "https://someUrl.com",
        "By Mohammed",
        "Abstract",
        "Android Studio",
        "Today",
        "Articles",
        "Fun",
        "Some Keywords", mutableListOf(), "Some date"
    )

    private val title =
        "Some long long long long long long long long long long long long long long long long long long long long title"
    private val shortTitle = "Title"


    @Test
    fun test_UrlValidation() {
        Assert.assertEquals(url1.isValidUrl(), false)
        Assert.assertEquals(url2.isValidUrl(), true)
        Assert.assertEquals(url3.isValidUrl(), true)
        Assert.assertEquals(url4.isValidUrl(), false)
        Assert.assertEquals(url5.isValidUrl(), false)
    }

    @Test
    fun test_GettingArticleDetails() {
        val articleDetails = article.getDetails()
        Assert.assertEquals(article.title, articleDetails.title)
        Assert.assertEquals(article.abstract, articleDetails.abstract)
        Assert.assertEquals(article.byline, articleDetails.byline)
        Assert.assertEquals(article.url, articleDetails.url)
    }

    @Test
    fun test_trimArticleTitle() {
        val newArticle = title.trimWithDots(60)
        Assert.assertEquals(
            "Should trim to 60 chars and add 3 dots, total will be 63",
            newArticle.length,
            63
        )
        val newArticle2 = shortTitle.trimWithDots(60)
        Assert.assertEquals(newArticle2.length, 5)
    }
}
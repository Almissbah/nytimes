package com.almissbbah.nytimes.di.module

import com.almissbbah.nytimes.data.remote.PopularArticlesApiService
import com.almissbbah.nytimes.data.repo.PopularArticlesRepo
import com.almissbbah.nytimes.data.repo.PopularArticlesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepoModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideAppRepository(
        articlesApiService: PopularArticlesApiService
    ): PopularArticlesRepository {
        return PopularArticlesRepo(
            articlesApiService
        )
    }

}
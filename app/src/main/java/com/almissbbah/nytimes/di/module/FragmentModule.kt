package com.almissbbah.nytimes.di.module


import com.almissbbah.nytimes.ui.details.ArticleDetailsFragment
import com.almissbbah.nytimes.ui.home.HomeFragment
import com.almissbbah.nytimes.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeArticleDetailsFragment(): ArticleDetailsFragment

}
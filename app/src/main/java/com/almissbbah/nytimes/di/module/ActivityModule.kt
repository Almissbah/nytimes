package com.almissbbah.nytimes.di.module

import com.almissbbah.nytimes.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

}
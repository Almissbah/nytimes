package com.almissbbah.nytimes.di.component

import ActivityModule
import android.app.Application
import com.almissbbah.nytimes.NyTimesApplication
import com.almissbbah.nytimes.di.module.ApiModule
import com.almissbbah.nytimes.di.module.FragmentModule
import com.almissbbah.nytimes.di.module.RepoModule
import com.almissbbah.nytimes.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, RepoModule::class, AndroidSupportInjectionModule::class, ViewModelModule::class, FragmentModule::class, ActivityModule::class])
interface AppComponent : AndroidInjector<NyTimesApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: NyTimesApplication)
}
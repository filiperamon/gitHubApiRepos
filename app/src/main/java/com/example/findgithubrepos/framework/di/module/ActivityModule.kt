package com.example.findgithubrepos.framework.di.module

import com.example.findgithubrepos.framework.di.provider.ListRepoGithubFragmentProvider
import com.example.findgithubrepos.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivityModule {
    @ContributesAndroidInjector(
        modules = [
            ListRepoGithubFragmentProvider::class
        ])
    fun mainActivityInjector(): MainActivity
}
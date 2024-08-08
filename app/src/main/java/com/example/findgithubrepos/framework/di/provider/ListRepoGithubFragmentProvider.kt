package com.example.findgithubrepos.framework.di.provider

import com.example.findgithubrepos.presentation.fragment.ListPullReposFragment
import com.example.findgithubrepos.presentation.fragment.ListReposGitHubFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ListRepoGithubFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideListReposGitHubFragment(): ListReposGitHubFragment

    @ContributesAndroidInjector
    abstract fun provideListPullReposFragment(): ListPullReposFragment
}
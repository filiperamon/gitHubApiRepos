package com.example.findgithubrepos.framework.di

import com.example.findgithubrepos.data.repository.ReposGitHubRepository
import com.example.findgithubrepos.data.repository.ReposGitHubRepositoryImpl
import com.example.findgithubrepos.data.repository.ReposRemoteDataSource
import com.example.findgithubrepos.domain.model.GitHubResponse
import com.example.findgithubrepos.framework.repository.RetrofitReposDataSource
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindReposRemoteRepository(repository: ReposGitHubRepositoryImpl): ReposGitHubRepository

    @Binds
    fun bindRemoteReposDataSource(
        dataSource: RetrofitReposDataSource
    ): ReposRemoteDataSource<GitHubResponse>
}
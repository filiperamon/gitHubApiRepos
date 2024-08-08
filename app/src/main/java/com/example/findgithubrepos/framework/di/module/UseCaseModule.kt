package com.example.findgithubrepos.framework.di.module

import com.example.findgithubrepos.domain.useCase.GetPullsReposUseCase
import com.example.findgithubrepos.domain.useCase.GetPullsReposUseCaseImpl
import com.example.findgithubrepos.domain.useCase.GetReposStarsGitHubUseCase
import com.example.findgithubrepos.domain.useCase.GetReposStarsGitHubUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindReposStarsGitHubUseCase(useCase: GetReposStarsGitHubUseCaseImpl): GetReposStarsGitHubUseCase

    @Binds
    fun bindGetPullsReposUseCase(useCase: GetPullsReposUseCaseImpl): GetPullsReposUseCase
}
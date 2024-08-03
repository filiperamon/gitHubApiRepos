package com.example.findgithubrepos.framework.di

import com.example.findgithubrepos.domain.useCase.GetReposStarsGitHubUseCase
import com.example.findgithubrepos.domain.useCase.GetReposStarsGitHubUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindGetReposStarsGitHubUseCaseImpl(useCase: GetReposStarsGitHubUseCaseImpl): GetReposStarsGitHubUseCase

}
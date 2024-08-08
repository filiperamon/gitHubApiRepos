package com.example.findgithubrepos.domain.useCase

import com.example.findgithubrepos.domain.model.GitHubResponse
import io.reactivex.Observable

interface GetReposStarsGitHubUseCase {
    fun gitHubResponseCallBack(language: String, sort: String, page: Int) : Observable<GitHubResponse>
}
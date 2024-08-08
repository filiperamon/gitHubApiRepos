package com.example.findgithubrepos.domain.useCase

import com.example.findgithubrepos.domain.model.GitHubResponse
import com.example.findgithubrepos.domain.model.PullRequestResponse
import io.reactivex.Observable

interface GetPullsReposUseCase {
    fun getListPullRepos(owner: String, repo: String) : Observable<List<PullRequestResponse>>
}
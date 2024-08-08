package com.example.findgithubrepos.data.repository

import com.example.findgithubrepos.domain.model.GitHubResponse
import com.example.findgithubrepos.domain.model.PullRequestResponse
import io.reactivex.Observable

interface ReposGitHubRepository {
    fun getListStarsJavaRepos(language: String, sort: String, page: Int) : Observable<GitHubResponse>
    fun getListPullRepos(owner: String, repo: String) : Observable<PullRequestResponse>
}
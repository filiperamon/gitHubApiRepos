package com.example.findgithubrepos.data.repository

import com.example.findgithubrepos.domain.model.GitHubResponse
import com.example.findgithubrepos.domain.model.PullRequestResponse
import io.reactivex.Observable
import javax.inject.Inject

class ReposGitHubRepositoryImpl @Inject constructor(
    private val remoteDataSource: ReposRemoteDataSource<GitHubResponse>
) : ReposGitHubRepository {
    override fun getListStarsJavaRepos(language: String, sort: String, page: Int) : Observable<GitHubResponse> {
        return remoteDataSource.getListStarsJavaRepos(language, sort, page)
    }

    override fun getListPullRepos(owner: String, repo: String): Observable<PullRequestResponse> {
        return remoteDataSource.getListPullRepos(owner, repo)
    }
}
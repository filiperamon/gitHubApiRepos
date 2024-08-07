package com.example.findgithubrepos.data.repository

import com.example.findgithubrepos.domain.model.GitHubResponse
import io.reactivex.Observable
import javax.inject.Inject

class ReposGitHubRepositoryImpl @Inject constructor(
    private val remoteDataSource: ReposRemoteDataSource<GitHubResponse>
) : ReposGitHubRepository {
    override fun getListStarsJavaRepos() : Observable<GitHubResponse> {
        return remoteDataSource.getListStarsJavaRepos()
    }
}
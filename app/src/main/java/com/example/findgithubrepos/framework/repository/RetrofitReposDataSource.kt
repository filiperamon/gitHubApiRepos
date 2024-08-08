package com.example.findgithubrepos.framework.repository

import com.example.findgithubrepos.data.repository.ReposRemoteDataSource
import com.example.findgithubrepos.domain.model.GitHubResponse
import com.example.findgithubrepos.framework.GitHubEndPoint
import io.reactivex.Observable
import javax.inject.Inject

class RetrofitReposDataSource @Inject constructor(
    private val gitHubEndPoint: GitHubEndPoint
) : ReposRemoteDataSource<GitHubResponse> {

    override fun getListStarsJavaRepos(language: String, sort: String, page: Int) : Observable<GitHubResponse> {
        return gitHubEndPoint.getListStarsJavaRepos(language, sort, page)
    }
}
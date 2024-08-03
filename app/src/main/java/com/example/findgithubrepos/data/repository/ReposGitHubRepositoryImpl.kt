package com.example.findgithubrepos.data.repository

import com.example.findgithubrepos.domain.model.ApiResult
import com.example.findgithubrepos.domain.model.GitHubResponse
import retrofit2.Call
import javax.inject.Inject

class ReposGitHubRepositoryImpl @Inject constructor(
    private val remoteDataSource: ReposRemoteDataSource<GitHubResponse>
) : ReposGitHubRepository {
    override suspend fun getListStarsJavaRepos(
        gitHubResponseCallBack: (result: ApiResult<GitHubResponse>) -> Unit
    ) {
        remoteDataSource.getListStarsJavaRepos(
            gitHubResponseCallBack
        )
    }
}
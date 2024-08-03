package com.example.findgithubrepos.data.repository

import com.example.findgithubrepos.domain.model.ApiResult
import com.example.findgithubrepos.domain.model.GitHubResponse
import retrofit2.Call

interface ReposRemoteDataSource<T> {
    suspend fun getListStarsJavaRepos(
        gitHubResponseCallBack: (result: ApiResult<GitHubResponse>) -> Unit
    )
}
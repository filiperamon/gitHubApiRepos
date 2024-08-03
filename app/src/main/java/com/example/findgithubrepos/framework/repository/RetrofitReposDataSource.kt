package com.example.findgithubrepos.framework.repository

import com.example.findgithubrepos.data.repository.ReposRemoteDataSource
import com.example.findgithubrepos.domain.model.ApiError
import com.example.findgithubrepos.domain.model.ApiException
import com.example.findgithubrepos.domain.model.ApiResult
import com.example.findgithubrepos.domain.model.ApiSuccess
import com.example.findgithubrepos.domain.model.GitHubResponse
import com.example.findgithubrepos.framework.GitHubEndPoint
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class RetrofitReposDataSource @Inject constructor(
    private val gitHubEndPoint: GitHubEndPoint
) : ReposRemoteDataSource<GitHubResponse> {

    override suspend fun getListStarsJavaRepos(
        gitHubResponseCallBack: (result: ApiResult<GitHubResponse>) -> Unit
    ){
        gitHubEndPoint.getListStarsJavaRepos()
            .enqueue(object: Callback<GitHubResponse> {
                override fun onResponse(
                    call: Call<GitHubResponse>,
                    response: retrofit2.Response<GitHubResponse>) {

                    if (response.isSuccessful) {
                        response.body()?.let {
                            gitHubResponseCallBack(ApiSuccess(it))
                        }
                    } else {
                        gitHubResponseCallBack(ApiError(response.code() ,response.message()))
                    }
                }

                override fun onFailure(call: Call<GitHubResponse>, t: Throwable) {
                    gitHubResponseCallBack(ApiException(t))
                }
            })
    }
}
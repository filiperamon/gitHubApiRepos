package com.example.findgithubrepos.framework

import com.example.findgithubrepos.domain.model.GitHubResponse
import retrofit2.Call
import retrofit2.http.GET

interface GitHubEndPoint {

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun getListStarsJavaRepos(): Call<GitHubResponse>
}
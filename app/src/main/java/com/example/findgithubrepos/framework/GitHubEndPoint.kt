package com.example.findgithubrepos.framework

import com.example.findgithubrepos.domain.model.GitHubResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface GitHubEndPoint {

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun getListStarsJavaRepos(): Observable<GitHubResponse>
}
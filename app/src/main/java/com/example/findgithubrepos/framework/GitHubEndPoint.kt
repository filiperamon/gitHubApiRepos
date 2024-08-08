package com.example.findgithubrepos.framework

import com.example.findgithubrepos.domain.model.GitHubResponse
import com.example.findgithubrepos.domain.model.PullRequestResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubEndPoint {

    @GET("search/repositories")
    fun getListStarsJavaRepos(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Observable<GitHubResponse>

    @GET("repos/{owner}/{repo}/pulls")
    fun getRepoPullsDetails(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Observable<PullRequestResponse>
}
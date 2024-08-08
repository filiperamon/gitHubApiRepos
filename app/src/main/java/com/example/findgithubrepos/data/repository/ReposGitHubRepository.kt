package com.example.findgithubrepos.data.repository

import com.example.findgithubrepos.domain.model.GitHubResponse
import io.reactivex.Observable

interface ReposGitHubRepository {
    fun getListStarsJavaRepos(language: String, sort: String, page: Int) : Observable<GitHubResponse>
}
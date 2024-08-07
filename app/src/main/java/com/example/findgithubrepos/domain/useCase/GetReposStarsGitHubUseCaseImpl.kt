package com.example.findgithubrepos.domain.useCase

import com.example.findgithubrepos.data.repository.ReposGitHubRepository
import com.example.findgithubrepos.domain.model.GitHubResponse
import io.reactivex.Observable
import javax.inject.Inject

class GetReposStarsGitHubUseCaseImpl @Inject constructor(
    private val reposGitHubRepository: ReposGitHubRepository
) : GetReposStarsGitHubUseCase {

    override fun gitHubResponseCallBack() : Observable<GitHubResponse> {
        return reposGitHubRepository.getListStarsJavaRepos()
    }
}
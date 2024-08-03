package com.example.findgithubrepos.domain.useCase

import com.example.findgithubrepos.data.repository.ReposGitHubRepository
import com.example.findgithubrepos.domain.model.ApiResult
import com.example.findgithubrepos.domain.model.GitHubResponse
import javax.inject.Inject

class GetReposStarsGitHubUseCaseImpl @Inject constructor(
    private val reposGitHubRepository: ReposGitHubRepository
) : GetReposStarsGitHubUseCase {

    override suspend fun invoke(gitHubResponseCallBack: (result: ApiResult<GitHubResponse>) -> Unit) {
        return reposGitHubRepository.getListStarsJavaRepos(gitHubResponseCallBack)
    }
}
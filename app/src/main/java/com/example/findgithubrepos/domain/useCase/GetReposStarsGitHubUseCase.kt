package com.example.findgithubrepos.domain.useCase

import com.example.findgithubrepos.domain.model.ApiResult
import com.example.findgithubrepos.domain.model.GitHubResponse

interface GetReposStarsGitHubUseCase {
    suspend operator fun invoke(
        gitHubResponseCallBack: (result: ApiResult<GitHubResponse>) -> Unit
    )
}
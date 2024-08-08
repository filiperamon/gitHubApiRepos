package com.example.findgithubrepos.domain.useCase

import com.example.findgithubrepos.data.repository.ReposGitHubRepository
import com.example.findgithubrepos.domain.model.GitHubResponse
import com.example.findgithubrepos.domain.model.PullRequestResponse
import io.reactivex.Observable
import javax.inject.Inject

class GetPullsReposUseCaseImpl @Inject constructor(
    private val reposGitHubRepository: ReposGitHubRepository
) : GetPullsReposUseCase {

    override fun getListPullRepos(owner: String, repo: String): Observable<PullRequestResponse> {
        return reposGitHubRepository.getListPullRepos(owner, repo)
    }
}
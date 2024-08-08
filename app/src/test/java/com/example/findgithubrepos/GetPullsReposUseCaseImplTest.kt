package com.example.findgithubrepos

import com.example.findgithubrepos.data.repository.ReposGitHubRepository
import com.example.findgithubrepos.domain.model.PullRequestResponse
import com.example.findgithubrepos.domain.useCase.GetPullsReposUseCaseImpl
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetPullsReposUseCaseImplTest {

    @Mock
    private lateinit var reposGitHubRepository: ReposGitHubRepository

    private lateinit var getPullsReposUseCaseImpl: GetPullsReposUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getPullsReposUseCaseImpl = GetPullsReposUseCaseImpl(reposGitHubRepository)
    }


    @Test
    fun getListPullRepos_returnsPullRequests() {
        val owner = "owner"
        val repo = "repo"
        val pullRequests = listOf(PullRequestResponse(
            url = "url",
            title = "title",
            createdAt = "createdAt",
            body = "body"
        ))
        `when`(reposGitHubRepository.getListPullRepos(owner, repo)).thenReturn(Observable.just(pullRequests))

        val testObserver = getPullsReposUseCaseImpl.getListPullRepos(owner, repo).test()

        testObserver.assertValue(pullRequests)
    }

    @Test
    fun getListPullRepos_emptyList() {
        val owner = "owner"
        val repo = "repo"
        val pullRequests = emptyList<PullRequestResponse>()
        `when`(reposGitHubRepository.getListPullRepos(owner, repo)).thenReturn(Observable.just(pullRequests))

        val testObserver = getPullsReposUseCaseImpl.getListPullRepos(owner, repo).test()

        testObserver.assertValue(pullRequests)
    }

    @Test
    fun getListPullRepos_error() {
        val owner = "owner"
        val repo = "repo"
        val error = Throwable("Error")
        `when`(reposGitHubRepository.getListPullRepos(owner, repo)).thenReturn(Observable.error(error))

        val testObserver = getPullsReposUseCaseImpl.getListPullRepos(owner, repo).test()

        testObserver.assertError(error)
    }
}
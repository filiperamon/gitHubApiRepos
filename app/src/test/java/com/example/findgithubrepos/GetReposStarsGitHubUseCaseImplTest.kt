package com.example.findgithubrepos

import com.example.findgithubrepos.data.repository.ReposGitHubRepository
import com.example.findgithubrepos.domain.model.GitHubResponse
import com.example.findgithubrepos.domain.model.OwnerResponse
import com.example.findgithubrepos.domain.model.RepositoryItemResponse
import com.example.findgithubrepos.domain.useCase.GetReposStarsGitHubUseCaseImpl
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetReposStarsGitHubUseCaseImplTest {

    @Mock
    private lateinit var reposGitHubRepository: ReposGitHubRepository

    private lateinit var getReposStarsGitHubUseCaseImpl: GetReposStarsGitHubUseCaseImpl

    private val mockGitHubResponse = GitHubResponse(
        totalCount = 1,
        incompleteResults = false,
        items = listOf(
            RepositoryItemResponse(
                id = 1,
                nodeId = "node_id",
                name = "name",
                fullName = "full_name",
                description = "description",
                private = false,
                owner = OwnerResponse(
                    avatarUrl = "avatar_url",
                    login = "login"
                ),
                forksCount = 1,
                stargazersCount = 1,
            )
        )
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getReposStarsGitHubUseCaseImpl = GetReposStarsGitHubUseCaseImpl(reposGitHubRepository)
    }

    @Test
    fun gitHubResponseCallBack_returnsGitHubResponse() {
        val language = "kotlin"
        val sort = "stars"
        val page = 1
        val gitHubResponse = mockGitHubResponse
        `when`(reposGitHubRepository.getListStarsJavaRepos(language, sort, page)).thenReturn(
            Observable.just(gitHubResponse))

        val testObserver = getReposStarsGitHubUseCaseImpl.gitHubResponseCallBack(language, sort, page).test()

        testObserver.assertValue(gitHubResponse)
    }

    @Test
    fun gitHubResponseCallBack_emptyResponse() {
        val language = "kotlin"
        val sort = "stars"
        val page = 1
        val gitHubResponse = mockGitHubResponse
        `when`(reposGitHubRepository.getListStarsJavaRepos(language, sort, page)).thenReturn(Observable.just(gitHubResponse))

        val testObserver = getReposStarsGitHubUseCaseImpl.gitHubResponseCallBack(language, sort, page).test()

        testObserver.assertValue(gitHubResponse)
    }

    @Test
    fun gitHubResponseCallBack_error() {
        val language = "kotlin"
        val sort = "stars"
        val page = 1
        val error = Throwable("Error")
        `when`(reposGitHubRepository.getListStarsJavaRepos(language, sort, page)).thenReturn(Observable.error(error))

        val testObserver = getReposStarsGitHubUseCaseImpl.gitHubResponseCallBack(language, sort, page).test()

        testObserver.assertError(error)
    }

}
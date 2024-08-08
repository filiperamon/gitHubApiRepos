package com.example.findgithubrepos.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findgithubrepos.domain.model.PullRequestResponse
import com.example.findgithubrepos.domain.model.RepositoryItemResponse
import com.example.findgithubrepos.domain.useCase.GetPullsReposUseCase
import com.example.findgithubrepos.domain.utils.addToCompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListPullsReposViewModel @Inject constructor(
    private var getPullsReposUseCase: GetPullsReposUseCase
) : ViewModel() {

    private val listPullRepos: MutableLiveData<PullRequestResponse> = MutableLiveData()
    val listPullReposLiveData: MutableLiveData<PullRequestResponse> get() = listPullRepos

    private val itemRepos: MutableLiveData<RepositoryItemResponse> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
    }

    fun setListReposLiveData(itemRepo: RepositoryItemResponse) {
        itemRepos.value = itemRepo

        getPullsRepos(itemRepo.owner.login, itemRepo.name)
    }

    private fun getPullsRepos(owner: String, repo: String) {
        getPullsReposUseCase.getListPullRepos(owner, repo)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate {
            }
            .subscribe({ response ->
                listPullRepos.value = response
            }, { e ->
            }).addToCompositeDisposable(compositeDisposable)
    }

}
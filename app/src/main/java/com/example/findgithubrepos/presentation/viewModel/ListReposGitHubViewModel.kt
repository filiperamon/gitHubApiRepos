package com.example.findgithubrepos.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.findgithubrepos.domain.model.RepositoryItemResponse
import com.example.findgithubrepos.domain.useCase.GetReposStarsGitHubUseCase
import com.example.findgithubrepos.domain.utils.addToCompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListReposGitHubViewModel @Inject constructor(
    private var getReposStarsGitHubUseCase: GetReposStarsGitHubUseCase
): ViewModel() {

    private val TAG = ListReposGitHubViewModel::class.java.simpleName

    private val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val iLoadingLiveData : LiveData<Boolean> get() = isLoading

    private val reposGitHubList: MutableLiveData<List<RepositoryItemResponse>> = MutableLiveData()
    val listReposGitHubLiveData : LiveData<List<RepositoryItemResponse>> get() = reposGitHubList

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
    }

    fun getReposStarsGitHub(page: Int) {

        isLoading.value = true

        getReposStarsGitHubUseCase.gitHubResponseCallBack(LANGUAGE_FILTER, SORT_FILTER, page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate {
                isLoading.value = false
            }
            .subscribe({ response ->
                reposGitHubList.value = response.items
            }, { e ->
                Log.e("ListReposGitHubViewModel", "response: ${e.message}")
            }).addToCompositeDisposable(compositeDisposable)
    }

    companion object {
        const val LANGUAGE_FILTER = "language:Java"
        const val SORT_FILTER = "stars"
    }
}
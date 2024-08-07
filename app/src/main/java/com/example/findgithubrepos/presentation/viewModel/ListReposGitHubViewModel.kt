package com.example.findgithubrepos.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

    val testTitle: MutableLiveData<String> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
    }

    fun getReposStarsGitHub() {
        getReposStarsGitHubUseCase.gitHubResponseCallBack()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate {
                Log.d("ListReposGitHubViewModel", "========================= FINISH =========================")
            }
            .subscribe({ response ->
                Log.d("ListReposGitHubViewModel", "response: $response")
            }, { e ->
                Log.e("ListReposGitHubViewModel", "response: ${e.message}")
            }).addToCompositeDisposable(compositeDisposable)
    }

}
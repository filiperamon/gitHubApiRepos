package com.example.findgithubrepos.framework.di.builder

import androidx.lifecycle.ViewModel
import com.example.findgithubrepos.framework.di.ViewModelKey
import com.example.findgithubrepos.presentation.viewModel.ListReposGitHubViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(ListReposGitHubViewModel::class)
    abstract fun bindListReposGitHubViewModel(listReposGitHubViewModel: ListReposGitHubViewModel): ViewModel

}
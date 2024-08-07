package com.example.findgithubrepos.framework.di.builder

import androidx.lifecycle.ViewModelProvider
import com.example.findgithubrepos.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [ViewModelsBuilder::class])
abstract class ViewModelFactoryBuilder {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
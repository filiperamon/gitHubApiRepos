package com.example.findgithubrepos.framework.di.module

import android.app.Application
import android.content.Context
import com.example.findgithubrepos.framework.di.builder.ViewModelFactoryBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ViewModelFactoryBuilder::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext
}
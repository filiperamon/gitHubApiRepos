package com.example.findrepositorygithub

import com.example.findgithubrepos.presentation.MainActivity
import com.example.findgithubrepos.framework.di.AppModule
import com.example.findgithubrepos.framework.di.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, AppModule::class])
interface ApiComponent {
    fun inject(activity: MainActivity?)
}
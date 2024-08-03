package com.example.findrepositorygithub

import android.app.Application
import com.example.findgithubrepos.BuildConfig
import com.example.findgithubrepos.framework.di.AppModule
import com.example.findgithubrepos.framework.di.NetworkModule

class MyApplication : Application() {
    private var mApiComponent: ApiComponent? = null

    override fun onCreate() {
        super.onCreate()
        mApiComponent = DaggerApiComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(BuildConfig.BASE_URL))
            .build()
    }

    fun getNetComponent(): ApiComponent {
        return mApiComponent!!
    }
}
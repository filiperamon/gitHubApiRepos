package com.example.findrepositorygithub

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.findgithubrepos.framework.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import kotlin.properties.Delegates

class MyApplication : Application(), HasActivityInjector {

    private val TAG = MyApplication::class.java.name
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
    override fun activityInjector() = activityInjector


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {

        var instance: MyApplication by Delegates.notNull()
    }

}
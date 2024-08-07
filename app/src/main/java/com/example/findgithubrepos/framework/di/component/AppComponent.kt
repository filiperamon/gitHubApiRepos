package com.example.findgithubrepos.framework.di.component

import android.app.Application
import com.example.findgithubrepos.framework.di.module.ActivityModule
import com.example.findgithubrepos.framework.di.module.AppModule
import com.example.findgithubrepos.framework.di.module.NetworkModule
import com.example.findgithubrepos.framework.di.module.RepositoryModule
import com.example.findgithubrepos.framework.di.module.UseCaseModule
import com.example.findrepositorygithub.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityModule::class,
    NetworkModule::class,
    UseCaseModule::class,
    RepositoryModule::class,
])
interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MyApplication)
}


package com.example.findgithubrepos.framework.di

import android.app.Application
import com.example.findgithubrepos.BuildConfig
import com.example.findgithubrepos.framework.GitHubEndPoint
import com.example.findrepositorygithub.MyApplication
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(
    private val mBaseUrl: String
) {

    @Provides
    fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else HttpLoggingInterceptor.Level.NONE
            )
        }
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .cache(provideHttpCache(MyApplication()))
            .build()
    }

    @Provides
    fun provideGsonConverterFactory() : GsonConverterFactory {
        return GsonConverterFactory
            .create()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ): GitHubEndPoint {
        return Retrofit.Builder()
            .baseUrl(mBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(GitHubEndPoint::class.java)
    }
}
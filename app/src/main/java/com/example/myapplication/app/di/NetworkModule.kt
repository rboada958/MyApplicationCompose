package com.example.myapplication.app.di

import android.content.Context
import android.util.Log
import com.example.myapplication.BuildConfig
import com.example.myapplication.app.data.LocalDataStore
import com.example.myapplication.app.networks.interceptor.ConnectivityInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val OK_HTTP_CACHE = "okhttp_cache"
    }

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideConnectivityInterceptor(@ApplicationContext context: Context): ConnectivityInterceptor {
        return ConnectivityInterceptor(context)
    }

    @Provides
    fun provideOkHttpClient(
        localDataStore: LocalDataStore,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {


        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${localDataStore.getAuthToken()}")
                    .addHeader("x-custom-lang", Locale.getDefault().language)
                Log.d("TOKEN", "provideOkHttpClient: ${localDataStore.getAuthToken()}")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .readTimeout(40, TimeUnit.MINUTES)
            .connectTimeout(40, TimeUnit.MINUTES)
            .callTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Named("old")
    fun provideOkHttpClientOld(
        localDataStore: LocalDataStore,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {


        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .addHeader(
                        "User-Agent",
                        "android/${BuildConfig.VERSION_NAME}"
                    )
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .addHeader("User-Agent", "android/${BuildConfig.VERSION_NAME}")
                    .addHeader("x-custom-lang", Locale.getDefault().language)
                Log.d("TOKEN", "provideOkHttpClient: ${localDataStore.getAuthToken()}")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .readTimeout(40, TimeUnit.MINUTES)
            .connectTimeout(40, TimeUnit.MINUTES)
            .callTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Named("images")
    fun provideOkHttpClientImages(
        localDataStore: LocalDataStore,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {


        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .addHeader(
                        "User-Agent",
                        "android/${BuildConfig.VERSION_NAME}"
                    )
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .addHeader("User-Agent", "android/${BuildConfig.VERSION_NAME}")
                    .addHeader("x-custom-lang", Locale.getDefault().language)
                Log.d("TOKEN", "provideOkHttpClient: ${localDataStore.getAuthToken()}")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .readTimeout(40, TimeUnit.MINUTES)
            .connectTimeout(40, TimeUnit.MINUTES)
            .callTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .build()
    }
}
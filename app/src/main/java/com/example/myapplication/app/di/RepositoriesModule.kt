package com.example.myapplication.app.di

import com.example.myapplication.app.data.LocalDataStore
import com.example.myapplication.app.networks.api.AuthApi
import com.example.myapplication.ui.login.mvvm.LoginRepository
import com.unitea.android.app.networks.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {
    @Provides
    fun providesLoginRepository(localDataStore: LocalDataStore, api: AuthApi): LoginRepository =
        LoginRepository(localDataStore, api)
}
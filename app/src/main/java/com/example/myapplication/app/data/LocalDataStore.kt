package com.example.myapplication.app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapplication.model.auth.LoginParams
import com.example.myapplication.model.auth.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class LocalDataStore @Inject constructor(val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    companion object PreferencesKeys {
        val ACCESS_TOKEN = stringPreferencesKey("accessTokenKey")
        val SESSION_INFO = stringPreferencesKey("sessionKey")
        val USER_DATA = stringPreferencesKey("userDataKey")
        val CREDENTIALS = stringPreferencesKey("credentialsKey")
    }

    fun getAuthToken() = runBlocking {
        context.dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN] ?: ""
        }.first()
    }


    suspend fun setAccessToken(token: String) {
        context.dataStore.edit { settings ->
            settings[ACCESS_TOKEN] = token
        }
    }

    suspend fun setUserCredentials(credentials: LoginParams) {
        context.dataStore.edit { settings ->
            settings[CREDENTIALS] = Gson().toJson(credentials)
        }
    }

    suspend fun setUserData(userData: String) {
        context.dataStore.edit { settings ->
            settings[USER_DATA] = userData
        }
    }

    fun getUserData() = runBlocking {
        Gson().fromJson(context.dataStore.data.map { preferences ->
            preferences[USER_DATA] ?: ""
        }.first(), User::class.java)
    }

    fun getLoginCredentials(): String = runBlocking {
        context.dataStore.data.map { preferences ->
            preferences[CREDENTIALS] ?: ""
        }.first()
    }

    fun deleteUserData() = runBlocking {
        context.dataStore.edit {
            it.clear()
        }
    }
}
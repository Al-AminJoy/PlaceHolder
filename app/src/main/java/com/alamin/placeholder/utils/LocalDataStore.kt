package com.alamin.placeholder.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map


const val PREFERENCE_NAME = "data_store"

class LocalDataStore(val context: Context ) {

    companion object PreferenceKeys{
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREFERENCE_NAME)
        val USER = stringPreferencesKey("user")
        val ID = intPreferencesKey("user_id")
        val NAME = stringPreferencesKey("user_name")
    }

    suspend fun storeUser(user: String){
        context.dataStore.edit {
            it[USER] = user;
        }
    }

    suspend fun storeId(id:Int){
        context.dataStore.edit {
            it[ID] = id
        }
    }

    suspend fun storeName(name: String){
        context.dataStore.edit {
            it[NAME] = name;
        }
    }

    fun getUser() = context.dataStore.data.map {
        it[USER]?:-1
    }

    fun getId() = context.dataStore.data.map {
        it[ID]?:-1
    }

    fun getName() = context.dataStore.data.map {
        it[NAME]?:-1
    }
}
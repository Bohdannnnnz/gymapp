package com.hm.gymapp.data.repository

import android.content.SharedPreferences
import com.hm.gymapp.data.service.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LocalRepository {

    override fun saveData(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getData(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    override fun removeData(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}
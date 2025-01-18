package com.hm.gymapp.data.service

interface LocalRepository {
    fun saveData(key: String, value: String)
    fun getData(key: String): String?
    fun removeData(key: String)
}
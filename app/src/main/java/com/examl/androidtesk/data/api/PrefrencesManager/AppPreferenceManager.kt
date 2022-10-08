package com.shawky.zimozitennisapptask.data.services.PrefrencesManager

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import androidx.preference.PreferenceManager.getDefaultSharedPreferences

class AppPreferenceManager(context: Context) {

    private var preferenceManager: SharedPreferences = getDefaultSharedPreferences(context)

    fun getStringData(key:String): String? {
        return preferenceManager.getString(key, "")
    }

    suspend inline fun <reified T>getSavedData(scope: CoroutineScope,key:String): T? {
        val data = scope.async(Dispatchers.IO) {
            var data  : T? = null
            val json = getStringData(key)
            data = if(json?.isNotEmpty() == true){
                Gson().fromJson(json, T::class.java) as T
            }else {
               null
            }
            Log.d("SharedPrefs", "Saved Data : $data")
            data
        }
        return data.await()

    }

    suspend inline fun <reified T> getSavedDataList(scope: CoroutineScope,key:String): ArrayList<T>? {
            val data = scope.async(Dispatchers.IO) {
                var listData  : ArrayList<T>? = null
                val json = getStringData(key)
                if (json?.isNotEmpty() == true) {
                    listData = Gson().fromJson<ArrayList<T>>(
                        json,
                        object : TypeToken<ArrayList<T>>() {}.type
                    )
                }
                Log.d("SharedPrefs", "Saved List Data : $listData")
                listData
            }
        return data.await()
    }

    fun storeData(key:String, data: Any) {
        val json = Gson().toJson(data)
        Log.d("SharedPrefs","Data Saved : $json")
        preferenceManager.edit().putString( key , json ).apply()
    }
}
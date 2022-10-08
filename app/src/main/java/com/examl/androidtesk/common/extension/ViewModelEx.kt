package com.examl.androidtesk.common.extension

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

fun <T> ViewModel.performNetworkOp(isNetworkConnected:Boolean,
                                     networkCall : suspend () -> T,
                                     doOnMainThread : suspend (T)-> Unit,
                                     onError : suspend (Exception?) -> Unit){
    viewModelScope.launch(Dispatchers.IO) {
        Log.d("isNetworkConnected",isNetworkConnected.toString())
        try {
            if (isNetworkConnected) {
                val data = networkCall()
                withContext(Dispatchers.Main) {
                    doOnMainThread(data)
                }
            }else{
                //Todo GetFrom Local
            }
        }catch (e: Exception){
            onError(e)
        }
    }
}
package com.examl.androidtesk.common.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

fun <T> ViewModel.performNetworkOp(isNetworkConnected:Boolean,
                                     networkCall : suspend () -> T,
                                     doOnMainThread : suspend (T)-> Unit,
                                     onError : (Exception?) -> Unit){
    viewModelScope.launch(Dispatchers.IO) {
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
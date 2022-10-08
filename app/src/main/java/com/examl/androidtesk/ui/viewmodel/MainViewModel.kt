package com.examl.androidtesk.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examl.androidtesk.common.extension.performNetworkOp
import com.examl.androidtesk.common.utils.Resource
import com.examl.androidtesk.data.model.PlayerModel
import com.examl.androidtesk.data.model.ResponseModel
import com.examl.androidtesk.data.repository.MainRepository
import com.examl.androidtesk.di.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepository: MainRepository,
    val networkHelper: NetworkHelper
): ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var _hasError = MutableLiveData<Boolean>()
    val hasError: LiveData<Boolean> get() = _hasError

    private var _contentList = MutableLiveData<Resource<ArrayList<PlayerModel>>>()
    val contentList: LiveData<Resource<ArrayList<PlayerModel>>> get() = _contentList


    init {
        fetchTennisPlayers()
    }

    fun fetchTennisPlayers(){
        _isLoading.postValue(true)

        performNetworkOp(
            isNetworkConnected = networkHelper.isNetworkConnected(),
            networkCall =  {
                Log.d("networkCall","networkCall")
                mainRepository.fetchTennisPlayers()
            },
            doOnMainThread = {
                Log.d("networkCall","networkCall ${it.status}")
                _isLoading.postValue(false)
                if (it.status){
                    _contentList.postValue(Resource.success(it.data))
                }else{
                    _contentList.value = Resource.error(it.errors?.first()?:it.message,it.data)
                }
            } ,
            onError = {
                _hasError.postValue(true)
            }
        )
    }
}
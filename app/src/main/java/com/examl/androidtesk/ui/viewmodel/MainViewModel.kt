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
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepository: MainRepository,
    val networkHelper: NetworkHelper
): ViewModel() {

    private var _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading = _isLoading

    private var _hasData = MutableStateFlow<Boolean>(true)
    val hasData: MutableStateFlow<Boolean> get() = _hasData

    private var _hasError = MutableStateFlow<Boolean>(false)
    val hasError: MutableStateFlow<Boolean> get() = _hasError

    private var _contentList = MutableStateFlow<Resource<ArrayList<PlayerModel>>>(Resource.loading(null))
    val contentList: MutableStateFlow<Resource<ArrayList<PlayerModel>>> get() = _contentList


    init {
        fetchTennisPlayers()
    }

    fun fetchTennisPlayers(){

        performNetworkOp(
            isNetworkConnected = networkHelper.isNetworkConnected(),
            networkCall =  {
                _isLoading.emit(true)
                mainRepository.fetchTennisPlayers()
            },
            doOnMainThread = {
                Log.d("doOnMainThread","doOnMainThread")
                _isLoading.emit(false)
                if (it.status){
                    hasData.emit(true)
                    _contentList.emit(Resource.success(it.data))
                }else{
                    _contentList.emit(Resource.error(it.errors?.first()?:it.message,it.data))
                }
            } ,
            onError = {
                _isLoading.emit(false)
                _hasError.emit(true)
            }
        )
    }
}
package com.examl.androidtesk.data.api

import com.examl.androidtesk.common.utils.Constants
import com.examl.androidtesk.data.model.PlayerModel
import com.examl.androidtesk.data.model.ResponseModel
import retrofit2.http.GET


interface ApiService {

    @GET(Constants.Routs.GET_TENNIS_PLAYER)
    fun fetchTennisPlayers() :ResponseModel<ArrayList<PlayerModel>>

}
package com.examl.androidtesk.data.repository

import com.examl.androidtesk.data.model.PlayerModel
import com.examl.androidtesk.data.model.ResponseModel

interface MainRepository {

    fun fetchTennisPlayers() : ResponseModel<ArrayList<PlayerModel>>

}
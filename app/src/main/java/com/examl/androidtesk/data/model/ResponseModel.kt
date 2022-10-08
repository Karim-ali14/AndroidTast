package com.examl.androidtesk.data.model

import com.google.gson.annotations.SerializedName

data class ResponseModel<T> (
    var status : Boolean,
    var message : String,
    var data : T,
    val errors : List<String>?,
)


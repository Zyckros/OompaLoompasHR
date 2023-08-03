package com.example.oompaloompashr.model


import com.google.gson.annotations.SerializedName

data class WorkersList(
    @SerializedName("current")
    var current: Int,
    @SerializedName("results")
    var results: List<Result>,
    @SerializedName("total")
    var total: Int
)
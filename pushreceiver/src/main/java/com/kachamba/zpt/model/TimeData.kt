package com.kachamba.zpt.model

import com.google.gson.annotations.SerializedName

data class TimeData(
    @SerializedName("time_in_app")
    private val time: Long
)

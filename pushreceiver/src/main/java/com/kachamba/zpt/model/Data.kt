package com.kachamba.zpt.model

import com.google.gson.annotations.SerializedName

data class Data(

    @SerializedName("sender_id")
    private val senderId: String

) {
    //@JvmName("getSenderId1")
    fun getSenderId(): String {
        return senderId
    }
}

package com.kachamba.zpt

import android.content.Context

private const val TAG = "PushCache"

class A6 {

    private val a6v0 = "push_cache"
    private val a6v1 = "registration_id"
    private val a6v3 = "subscribe_status"
    private val a6v4 = "internal_id"
    private val a6v5 = "tag_key"
    private val a6v6 = "tag_value"

    private val appContext = A1.f1GC()
    private val ed = appContext
        .getSharedPreferences(a6v0, Context.MODE_PRIVATE)
        ?.edit()

    fun f0STK(key: String) {
        ed?.putString(a6v5, key)
        ed?.apply()
    }

    fun f1GTK(): String? {
        return appContext
            .getSharedPreferences(a6v0, Context.MODE_PRIVATE)
            ?.getString(a6v5, null)
    }

    fun f2STV(tagValue: String) {
        ed?.putString(a6v6, tagValue)
        ed?.apply()
    }

    fun f3GTV(): String? {
        return appContext
            .getSharedPreferences(a6v0, Context.MODE_PRIVATE)
            ?.getString(a6v6, null)
    }

    fun f4SII(internalId: String) {
        ed?.putString(a6v4, internalId)
        ed?.apply()
    }

    fun f5GIIFP(): String? {
        return appContext
            .getSharedPreferences(a6v0, Context.MODE_PRIVATE)
            ?.getString(a6v4, null)
    }

    fun f6SORIP(reg_id: String) {
        ed?.putString(a6v1, reg_id)
        //Log.d(TAG, "saveRegistrationIdPref: reg_id = $reg_id")
        ed?.apply()
    }

    fun f7GORIFP(): String? {
        return appContext
            .getSharedPreferences(a6v0, Context.MODE_PRIVATE)
            ?.getString(a6v1, null)
    }

    fun f8SSS(status: Boolean) {
        ed?.putBoolean(a6v3, status)
        ed?.apply()
    }
    fun f9GSSFP(): Boolean? {
        return appContext
            .getSharedPreferences(a6v0, Context.MODE_PRIVATE)
            ?.getBoolean(a6v3, false)
    }
}
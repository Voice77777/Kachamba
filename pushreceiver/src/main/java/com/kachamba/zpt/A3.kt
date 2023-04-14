package com.kachamba.zpt

import android.content.Context

class A3(context: Context) {
    private val mContext = context
    private val a3v0 = "helper_cache"
    private val a3v1 = "sent_push_id"
    private val a3v2 = "open-url"
    private val a3v3 = "deeplink"

    private val ed = mContext
        .getSharedPreferences(a3v0, Context.MODE_PRIVATE)
        ?.edit()

    fun f0SSPI(sentPushId: String?) {
        ed?.putString(a3v1, sentPushId)
        ed?.apply()
    }

    fun f1GSPI(): String? {
        return mContext
            .getSharedPreferences(a3v0, Context.MODE_PRIVATE)
            .getString(a3v1, null)
    }

    fun f2SOU(openUrl: String) {
        ed?.putString(a3v2, openUrl)
        ed?.apply()
    }

    fun f3GOU(): String? {
        return mContext
            .getSharedPreferences(a3v0, Context.MODE_PRIVATE)
            .getString(a3v2, null)
    }

    fun f4SD(deeplink: String) {
        ed?.putString(a3v3, deeplink)
        ed?.apply()
    }

    fun f5GD(): String? {
        return mContext
            .getSharedPreferences(a3v0, Context.MODE_PRIVATE)
            .getString(a3v3, null)
    }
}
package com.kachamba.zpt

import android.annotation.SuppressLint
import android.content.Context

class A1 {

    companion object {

        @SuppressLint("StaticFieldLeak")
        private lateinit var a1v0: Context

        fun f0SC(appContext: Context) {
            a1v0 = appContext
        }

        fun f1GC(): Context {
            return a1v0
        }
    }

}
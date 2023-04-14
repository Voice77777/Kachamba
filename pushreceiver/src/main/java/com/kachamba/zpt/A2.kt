package com.kachamba.zpt

import java.util.*

private const val TAG = "Repository"

class A2 {

    companion object {

        private var a2v0: Long = 0
        private var a2v1: Long = 0

        private val a2v2 = A6()
        val a2v3 = a2v2.f7GORIFP()

        fun f0GD(): Long {
            val date = Calendar.getInstance().timeInMillis
            a2v1 = date

            return (a2v1 - a2v0) / 1000
        }

        fun f1ST() {
            val date = Calendar.getInstance().timeInMillis
            a2v0 = date
            //Log.d(TAG, "onCreate: startTime = $startTime")
        }
    }
}
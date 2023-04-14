package com.kachamba.zpt

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.kachamba.zpt.A2.Companion.f1ST
import com.kachamba.zpt.model.PushData
import com.kachamba.zpt.model.TimeData
import com.kachamba.zpt.network.ApiHelper
import com.kachamba.zpt.network.RetrofitBuilder
import java.util.*


private const val TAG = "PushDevonics"
private const val PERMISSIONS_REQUEST_CODE = 2

class A0(activity: Activity, appId: String) {

    private val a0v0 = ApiHelper(RetrofitBuilder.apiService)
    private val a0v1 = activity
    private val a3 = A3(activity)
    private val a0v3 = appId
    private val a0v4 = activity

    init {
        A1.f0SC(activity)
        f4CII()
    }

    fun f0CP() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(a0v4, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // allow permission
                A8.run(a0v3, a0v0)
                f1ST()
                f6SS()
                f1ST(a0v0)
                Log.d(TAG, "Allow permission")
                // FCM SDK (and your app) can post notifications.
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(
                    a0v4,
                    Manifest.permission.POST_NOTIFICATIONS
                )
            ) {
                // permission denied
                Log.d(TAG, "Permission denied")
            } else {
                // ask permission
                Log.d(TAG, "Ask permission")
                a0v4.requestPermissions(
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    PERMISSIONS_REQUEST_CODE
                )
                if (ContextCompat.checkSelfPermission(a0v4, Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    Log.d(TAG, "Create user")
                    A8.run(a0v3, a0v0)
                    f1ST()
                    f6SS()
                    f1ST(a0v0)
                }
            }
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            Log.d(TAG, "Ask permission for API < TIRAMISU")
            A8.run(a0v3, a0v0)
            f1ST()
            f6SS()
            f1ST(a0v0)
        }
    }

    private fun f1ST(service: ApiHelper) {

        val a0v5 = a3.f1GSPI()
        val a6 = A6()
        val a0v7 = a6.f7GORIFP()
        if (a0v5 != "" || a0v5 != null) {
            val a0v8 = a0v5?.let { PushData(it) }
            if (a0v8 != null) {
                if (a0v7 != null) {
                    service.createTransition(a0v7, a0v8)
                }
            }
        }
        a3.f0SSPI(null)

    }

    fun f2OU() {
        val a0v9 = a3.f3GOU()
        if (a0v9 == "") {
            return
        }

        //Log.v(TAG, "openUrl: openUrl = $a0v9")
        if (a0v9 != null) {

            val a0v10 = Intent()
                .setAction(Intent.ACTION_VIEW)
                .addCategory(Intent.CATEGORY_BROWSABLE)
                .setData(Uri.parse(a0v9))

            //Log.d(TAG, "openUrl: Uri.parse = ${Uri.parse(a0v9)}")
            a0v10.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            try {
                a0v1.startActivity(a0v10)
            } catch (e: ActivityNotFoundException) {
                Log.e(TAG, "ActivityNotFoundException $e")
            }
        }

        a3.f2SOU("")
        //Log.d(TAG, "openUrl = $a0v9")
    }

    fun f3GD(): String {
        val a0v11 = a3.f5GD()
        //Log.d(TAG, "getDeeplink: deep1 = $a0v11")
        a3.f4SD("")
        return a0v11.toString()
    }

    private fun f4CII() {
        val a6 = A6()

        var a0v13 = a6.f5GIIFP()
        if (a0v13 == null) {
            val a0v14 = UUID.randomUUID()
            a0v13 = a0v14.toString()
            //Log.d(TAG, "createInternalId: internalId = $a0v13")
            a6.f4SII(a0v13)
        }

        //checkPermission(appId, activity)
    }

    fun f5GII(): String? {
        val a6 = A6()
        //Log.d(TAG, "getInternalId: internalId = ${a6.f5GIIFP()}")
        return a6.f5GIIFP()
    }

    //Be Public
    fun f6SS() {
        //Log.d(TAG, "startSession: ")
        val a6 = A6()
        val registrationId = a6.f7GORIFP()
        if (a6.f9GSSFP() == true) {
            registrationId?.let { a0v0.createSession(it) }
            //Log.d(TAG, "subscribeStatus = ${pushCache.getSubscribeStatusFromPref()}")

        }
    }

    fun f7SS() {
        val a0v15 = A2.f0GD()
        val a6 = A6()
        val regId = a6.f7GORIFP()
        if (regId != null) {
            val a0v16 = TimeData(a0v15)
            a0v0.sendTimeStatistic(regId, a0v16)
            //Log.d(TAG, "stopSession: timeData $timeData")
        }

        //Log.d(TAG, "stopSession: duration $duration")
        //Log.d(TAG, "stopSession: regId $regId")
        //Log.d(TAG, "stopSession")
    }

    fun fST(key: String, value: String) {
        val a6 = A6()
        if (key == null && value == null) {
            a6.f0STK("")
            a6.f2STV("")
        } else {
            a6.f0STK(key)
            a6.f2STV(value)
        }
    }
}

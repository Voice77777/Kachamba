package com.kachamba.zpt.network

import android.util.Log
import com.kachamba.zpt.A6
import com.kachamba.zpt.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

private const val TAG = "ApiHelper"

class ApiHelper(private val apiService: ApiService) {

    fun getSenderData(appId: String): String? {
        val call = apiService.getSenderId(appId)

        try {
            val response = call.execute()
            return response.body()?.data?.getSenderId()
            //Log.d(TAG, "getSenderData: response = $response")
        } catch (e: IOException) {
            e.printStackTrace()
            //Log.d(TAG, "getSenderData: IOException = $e")
        }
        return null
    }

    fun createPush(pushUser: PushUser): Status? {
        val call = apiService.createPush(pushUser)
        call.enqueue(
            object : Callback<Status> {
                override fun onResponse(
                    call: Call<Status>, response: Response<Status>) {

                    val a6 = A6()
                    if (response.isSuccessful) {
                        a6.f8SSS(true)
                        val registrationId = a6.f7GORIFP()
                        if (registrationId != null) {
                            createSession(registrationId)
                        }
                        Log.d(TAG, "createPush.onResponse: isSuccessful")
                    }
                }

                override fun onFailure(call: Call<Status>, t: Throwable) {
                    //Log.d(TAG, "createPush.onFailure: Throwable = $t")
                }

            })
        return null
    }

    fun createSession(registrationId: String): Status? {
        val call = apiService.createSession(registrationId)

        call.enqueue(
            object : Callback<Status> {
                override fun onResponse(call: Call<Status>, response: Response<Status>) {
                    val a6 = A6()

                    if (response.isSuccessful) {
                        val key = a6.f1GTK()
                        val value = a6.f3GTV()
                        if (!key.equals("") && !value.equals("")) {
                            if (key != null) {
                                if (value != null) {
                                    saveTag(key, value, registrationId)
                                    a6.f0STK("")
                                    a6.f2STV("")
                                }
                            }
                        }
                        Log.d(TAG, "createSession.onResponse: isSuccessful")
                    } else {
                        createSession(registrationId)
                    }
                    //Log.d(TAG, "createSession.onResponse: response = $response")
                }

                override fun onFailure(call: Call<Status>, t: Throwable) {
                    //Log.d(TAG, "createSession.onFailure: t = $t")
                }
            }
        )
        return null
    }

    fun updateRegistrationId(registrationId: String): Status? {
        val call = apiService.updateUser(registrationId)
        call.enqueue(
            object : Callback<Status> {
                override fun onResponse(
                    call: Call<Status>, response: Response<Status>) {
                    //Log.d(TAG, "updateRegistrationId.onResponse: response = $response")
                }

                override fun onFailure(call: Call<Status>, t: Throwable) {
                    //Log.d(TAG, "updateRegistrationId.onFailure: t = $t")
                }

            }
        )
        return null
    }

    fun saveTag(key: String, value: String, registrationId: String): Status? {

        val tag = Tag(key, value)

        val call = apiService.saveCustomParams(registrationId, tag)

        Log.d(TAG, "saveCustomTag: tag = $tag")

        call.enqueue(
           object : Callback<Status> {
               override fun onResponse(call: Call<Status>, response: Response<Status>) {
                   //Log.d(TAG, "saveCustomTag.onResponse: response = $response")
               }

               override fun onFailure(call: Call<Status>, t: Throwable) {
                   //Log.d(TAG, "saveCustomTag.onFailure: Throwable = $t")
               }
           }
        )
        return null
    }

    fun sendTimeStatistic(registrationId: String, timeData: TimeData): Status? {
        val call = apiService.sendDuration(registrationId, timeData)
        //Log.d(TAG, "sendTimeStatistic: timeData = $timeData")
        call.enqueue(
            object : Callback<Status> {
                override fun onResponse(call: Call<Status>, response: Response<Status>) {
                    //Log.d(TAG, "sendTimeStatistic.onResponse: response = $response")
                }

                override fun onFailure(call: Call<Status>, t: Throwable) {
                    //Log.d(TAG, "sendTimeStatistic.onFailure: Throwable = $t")
                }
            }
        )
        return null
    }

    fun createTransition(registrationId: String, pushData: PushData): Status? {
        val call = apiService.createTransition(registrationId, pushData)
        //Log.d(TAG, "createTransition: registrationId = $registrationId")
        call.enqueue(
            object : Callback<Status> {
                override fun onResponse(call: Call<Status>, response: Response<Status>) {
                }

                override fun onFailure(call: Call<Status>, t: Throwable) {
                    //Log.d(TAG, "createTransition.onFailure: Throwable = $t")
                }
            }
        )
        return null
    }
}
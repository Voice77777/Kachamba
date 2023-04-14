package com.kachamba.zpt;

import android.annotation.SuppressLint;
import android.app.Activity;

public class PushCallbackAndroid {
    @SuppressLint("StaticFieldLeak")
    private static A0 a0;

    public static void initPush(String appId, Activity activity) {
        a0 = new A0(activity, appId);
    }

    public static void onResume() {
        a0.f0CP();
    }

    /*public static void onResume() {
        pushDevonics.startSession();
    }*/

    public static void onStop() {
        a0.f7SS();
    }

    public static void sendTag(String key, String value) {
        a0.fST(key, value);
    }

    public static String getInternalID() {
        return a0.f5GII();
    }
    public static String getDeeplink() {
        return a0.f3GD();
    }
    public static void openPushUrl() {
        a0.f2OU();
    }
}

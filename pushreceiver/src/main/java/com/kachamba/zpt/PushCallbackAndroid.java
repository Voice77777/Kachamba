package com.kachamba.zpt;

import android.annotation.SuppressLint;
import android.app.Activity;

public class PluginMessage {
    @SuppressLint("StaticFieldLeak")
    private static A0 a0;

    public static void activate(String appId, Activity activity) {
        a0 = new A0(activity, appId);
    }

    public static void goBack() {
        a0.f0CP();
    }
    public static void goStop() {
        a0.f7SS();
    }
    public static String goUserID() {
        return a0.f5GII();
    }
    
}

package com.shisigui.jinmuyan;

import android.app.Application;
import android.content.Context;

import okhttp3.OkHttpClient;


public class MyApplication extends Application {
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();


    }
}

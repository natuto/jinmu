package com.shisigui.jinmuyan.model;

import android.util.Log;

import com.shisigui.jinmuyan.MyApplication;
import com.shisigui.jinmuyan.utils.NetStateUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.shisigui.jinmuyan.utils.NetStateUtils.isNetworkAvailable;

/**
 * Created by song on 2017/9/16.
 */

public class Api {


    public static String getAnalysis(String url){
        String s = null;
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(MyApplication.sContext.getExternalCacheDir(), cacheSize);
       // OkHttpClient client = MyApplication.okHttpClient;
       OkHttpClient client = new OkHttpClient.Builder().cache(cache).addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();


                Request requst = chain.request();
                if (!NetStateUtils.isNetworkAvailable(MyApplication.sContext)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (isNetworkAvailable(MyApplication.sContext)) {
                    int maxAge = 60 * 60 * 24;
                    response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();

                } else {
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();

                }
                return response;

            }
        }).build();
        final Request request = new Request.Builder().url(url).build();
        try {
           Response response=  client.newCall(request).execute();
         //  s = response.body().string();
            byte[] responseBytes=response.body().bytes();

            s = new String(responseBytes,"GBK");
          //  System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return s;
    }
}

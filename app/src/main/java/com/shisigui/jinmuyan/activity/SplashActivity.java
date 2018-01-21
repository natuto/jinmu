package com.shisigui.jinmuyan.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shisigui.jinmuyan.R;
import com.umeng.analytics.MobclickAgent;


public class SplashActivity extends AppCompatActivity {
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            goHome();

        }
    };
    public void goHome(){
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iniview();
    }

    private void iniview() {
        Handler handler = new Handler();
        handler.postDelayed(mRunnable,2000);

    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}

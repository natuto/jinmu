package com.shisigui.jinmuyan.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.hjm.bottomtabbar.BottomTabBar;
import com.shisigui.jinmuyan.R;
import com.shisigui.jinmuyan.fragment.AboutFragment;
import com.shisigui.jinmuyan.fragment.HomeFragment;
import com.shisigui.jinmuyan.fragment.VedioFragment;
import com.umeng.analytics.MobclickAgent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity {
    private BottomTabBar mBottomTabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomTabBar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);


        mBottomTabBar.init(getSupportFragmentManager())
                .addTabItem("主页", R.drawable.home_tab, HomeFragment.class)
                .addTabItem("视频", R.drawable.vedio_tab, VedioFragment.class)
                .addTabItem("关于", R.drawable.about_tab, AboutFragment.class);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);





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

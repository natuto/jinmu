package com.shisigui.jinmuyan.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.shisigui.jinmuyan.R;

/**
 * Created by song on 2017/9/7.
 */

public class VedioFragment extends Fragment implements View.OnClickListener {


    private Button mButton4;
    private Button mButton3;
    private Button mButton2;
    private Button mButton1;
    private TextView mTvpwd3;
    private Button mBotton6;
    private Button mBotton5;
    private TextView mTvpwd2;
    private TextView mTvpwd1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vedio_fragment, container, false);
        mTvpwd3 = (TextView) view.findViewById(R.id.tv_pwd_3);
        mBotton6 = (Button) view.findViewById(R.id.botton_6);
        mBotton5 = (Button) view.findViewById(R.id.botton_5);
        mTvpwd1 = (TextView) view.findViewById(R.id.tv_pwd_1);
        mTvpwd2 = (TextView) view.findViewById(R.id.tv_pwd_2);
        mButton4 = (Button) view.findViewById(R.id.botton_4);
        mButton3 = (Button) view.findViewById(R.id.botton_3);
        mButton2 = (Button) view.findViewById(R.id.botton_2);
        mButton1 = (Button) view.findViewById(R.id.botton_1);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mBotton5.setOnClickListener(this);
        mBotton6.setOnClickListener(this);
    }

    public void initStatusBar() {
        Window window = getActivity().getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(Color.parseColor("#EE4000"));
        ViewGroup mContentView = (ViewGroup) getActivity().findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {

            ViewCompat.setFitsSystemWindows(mChildView, true);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.botton_1:

                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://pan.baidu.com/s/1boKJb4r"));
                getActivity().startActivity(intent);
                break;
            case R.id.botton_2:
                mTvpwd1.setVisibility(View.VISIBLE);
                break;
            case R.id.botton_3:

                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://pan.baidu.com/s/1cD9LLW"));
                getActivity().startActivity(intent);
                break;
            case R.id.botton_4:
                mTvpwd2.setVisibility(View.VISIBLE);
                break;
            case R.id.botton_5:

                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://pan.baidu.com/s/1jIMgYUi"));
                getActivity().startActivity(intent);
                break;
            case R.id.botton_6:
                mTvpwd3.setVisibility(View.VISIBLE);
                break;

        }

    }
}

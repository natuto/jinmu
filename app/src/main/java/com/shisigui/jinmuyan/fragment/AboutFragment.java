package com.shisigui.jinmuyan.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shisigui.jinmuyan.R;

/**
 * Created by song on 2017/9/7.
 */

public class AboutFragment extends Fragment implements View.OnClickListener {

    private Button mBotton10;
    private Button mBotton9;
    private Button mBotton8;
    private Button mBotton7;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_fragment, container, false);
        mBotton10 = (Button) view.findViewById(R.id.botton_10);

        mBotton9 = (Button) view.findViewById(R.id.botton_9);

        mBotton8 = (Button) view.findViewById(R.id.botton_8);

        mBotton7 = (Button) view.findViewById(R.id.botton_7);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBotton7.setOnClickListener(this);
        mBotton8.setOnClickListener(this);
        mBotton9.setOnClickListener(this);
        mBotton10.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent =new Intent();
        switch (view.getId()) {
            case R.id.botton_7:
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.57mh.com/7366/"));
                getActivity().startActivity(intent);
                break;
            case R.id.botton_8:
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://comic.kukudm.com/comiclist/1393/index.htm"));
                getActivity().startActivity(intent);
                break;
            case R.id.botton_9:
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://ssg.feiwan.net/xiangguan/"));
                getActivity().startActivity(intent);
                break;
            case R.id.botton_10:
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.manhuagui.com/comic/16058/"));
                getActivity().startActivity(intent);
                break;
        }
    }
}

package com.shisigui.jinmuyan.fragment;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shisigui.jinmuyan.MyApplication;
import com.shisigui.jinmuyan.R;
import com.shisigui.jinmuyan.adapter.CatalogRecyclerAdapter;
import com.shisigui.jinmuyan.bean.Data;
import com.shisigui.jinmuyan.model.Api;
import com.shisigui.jinmuyan.view.DividerItemDecoration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.shisigui.jinmuyan.R.id.toolbar;
import static com.shisigui.jinmuyan.model.Api.getAnalysis;

/**
 * Created by song on 2017/9/7.
 */

public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private Toolbar mTofolbar;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private static  final  String URL = "http://ssg.feiwan.net/xiangguan";
    private List<Data> mDatas;
    private RecyclerView.LayoutManager mLayoutManager;
    private CatalogRecyclerAdapter mCatalogRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.home_fragment, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        mTofolbar = (Toolbar)view.findViewById(toolbar);
        mCollapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mCollapsingToolbar != null) {

            mCollapsingToolbar.setContentScrimColor(Color.parseColor("#00CCCCCC"));
            mCollapsingToolbar.setCollapsedTitleTextColor(Color.RED);
            mCollapsingToolbar.setExpandedTitleColor(Color.parseColor("#9A32CD"));

            mCollapsingToolbar.setTitle("东京食尸鬼RE");
        }

        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);
        intData();







    }
    public void intData() {
        mDatas = new ArrayList<>();
        new MyAsyncTask().execute();



    }
   public class MyAsyncTask extends AsyncTask {


       @Override
       protected Object doInBackground(Object[] objects) {
           Document document = null;

          // System.out.println(Api.getAnalysis(URL));

           document = (Document) Jsoup.parse(Api.getAnalysis(URL));

           Elements element = document.select("ul.articletxt3").select("li");
           for (int i = 1; i < element.size(); i++) {
               Data data = new Data();
            //   System.out.println(element.size());
               data.setTitle(element.get(i).select("a").attr("title"));
               data.setText(element.get(i).select("a").text());
               data.setUrl(element.get(i).select("a").attr("href"));
             //  System.out.println(data.getTitle());
            //   System.out.println(data.getUrl());
               mDatas.add(data);
           }
           return null;
       }

       @Override
       protected void onPostExecute(Object o) {
           super.onPostExecute(o);
           mCatalogRecyclerAdapter = new CatalogRecyclerAdapter(mDatas,getContext());
           mRecyclerView.setAdapter(mCatalogRecyclerAdapter);
           mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.HORIZONTAL,
                   2,getResources().getColor(R.color.colorBlack)));



       }
   }
}
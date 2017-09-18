package com.shisigui.jinmuyan.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shisigui.jinmuyan.R;
import com.shisigui.jinmuyan.adapter.CatalogRecyclerAdapter;
import com.shisigui.jinmuyan.adapter.PictureRecyclerAdapter;
import com.shisigui.jinmuyan.bean.Data;
import com.shisigui.jinmuyan.bean.ReUrl;
import com.shisigui.jinmuyan.model.Api;
import com.shisigui.jinmuyan.view.DividerItemDecoration;
import com.umeng.analytics.MobclickAgent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.R.attr.data;
import static android.R.attr.src;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class PictureActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private Data mData;
    private ReUrl mReUrl;
    private Context mContext = this;
    private PictureRecyclerAdapter mPictureRecyclerAdapter;
    private RecyclerView.LayoutManager mManager;
    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextTitle;
    private TextView mTextShow;
    private LinearLayout mLinearLayout;
    private Toolbar mTooflbar;
    private boolean is = true ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        mRecyclerView = (RecyclerView)findViewById(R.id.pic_recyclerview);
        mLinearLayout =  (LinearLayout)findViewById(R.id.linear_show);
        mTextView1 =  (TextView)findViewById(R.id.text1);
        mTextView2 =  (TextView)findViewById(R.id.text2);
        mTextTitle =  (TextView)findViewById(R.id.tv_title);
        mTextShow =  (TextView)findViewById(R.id.text_show);
        mTooflbar = (Toolbar) findViewById(R.id.toobar_pic);
        is=true;
        mData = (Data) getIntent().getSerializableExtra("data");
        mTooflbar.setTitle("");
        setSupportActionBar(mTooflbar);

        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mTextView1.setOnClickListener(this);
        mTextView2.setOnClickListener(this);
        mTextShow.setOnClickListener(this);
        mTextTitle.setText(mData.getText());

        mManager = new LinearLayoutManager(mContext);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 1){
                    mLinearLayout.setVisibility(View.INVISIBLE);
                    mTooflbar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
       // linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);


        initAnalysis();
    }
    public void initAnalysis(){

        new MyAsyncTask().execute();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text1:
                is = false;
                mData.setUrl(mReUrl.getPrevious());
                new MyAsyncTask().execute();
                break;
            case R.id.text2:
                is = false;
                mData.setUrl(mReUrl.getNext());
                new MyAsyncTask().execute();
                break;
            case R.id.text_show:
                mLinearLayout.setVisibility(View.VISIBLE);
                mTooflbar.setVisibility(View.VISIBLE);
                break;
        }

    }


    public class MyAsyncTask extends AsyncTask {


        @Override
        protected Object doInBackground(Object[] objects) {
            Document document = null;

                document = (Document) Jsoup.parse(Api.getAnalysis(mData.getUrl()));


            mReUrl = new ReUrl();

            Elements element1 = document.select("ul.collect2").select("li");
            Elements elements = document.getElementsByTag("script");
           // System.out.println("shang==========="+);
          // System.out.println("xia==========="+ element1.get(6).select("a").attr("href"));
            mReUrl.setPrevious(element1.get(0).select("a").attr("href"));
            mReUrl.setNext(element1.get(6).select("a").attr("href"));
            String[] strings = elements.get(10).data().toString().split("var");
           // System.out.println(strings[1]);
            String regex = "'(.*)'";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(strings[1]);
          // / System.out.println("------------");
            List<String > str = new ArrayList<>();
            while (matcher.find()) {
               str.add(matcher.group(1));
            }
            mReUrl.setUrl(str);

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (is) {
                mPictureRecyclerAdapter = new PictureRecyclerAdapter(mContext , mReUrl);
                mRecyclerView.setLayoutManager(mManager);
                mRecyclerView.setAdapter(mPictureRecyclerAdapter);
            }else {

                mPictureRecyclerAdapter.upData(mReUrl);

            }




        }

        }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
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

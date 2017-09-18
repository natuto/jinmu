package com.shisigui.jinmuyan.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.shisigui.jinmuyan.MyApplication;
import com.shisigui.jinmuyan.R;
import com.shisigui.jinmuyan.activity.PictureActivity;
import com.shisigui.jinmuyan.bean.Data;

import java.util.List;

/**
 * Created by song on 2017/9/8.
 */

public class CatalogRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Data> mData;
    private Context mContext;
    public CatalogRecyclerAdapter(List<Data> datas ,Context context) {
    this.mData = datas;
        this.mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),R.layout.item,null);

        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder)holder;
        myHolder.mTextView.setText(mData.get(position).getTitle());
        myHolder.BindItemClick(mData.get(position));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    private class  MyHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;


        public MyHolder(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView.findViewById(R.id.textView);

        }
        public  void BindItemClick(final Data data){
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(mContext, PictureActivity.class);
                    intent.putExtra("data",data);
                    mContext.startActivity(intent);

                }
            });

        }


    }

}

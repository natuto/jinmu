package com.shisigui.jinmuyan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shisigui.jinmuyan.R;
import com.shisigui.jinmuyan.bean.ReUrl;
import com.shisigui.jinmuyan.view.DoubleScaleImageView;

/**
 * Created by song on 2017/9/15.
 */

public class PictureRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ReUrl mReUrl;
    private Context mContext;



    public PictureRecyclerAdapter(Context context , ReUrl reUrl) {
        this.mContext = context;
        this.mReUrl = reUrl;




    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // View view = View.inflate(parent.getContext(),R.layout.picture_rv_item,null);
        View view = LayoutInflater.from(mContext).inflate(R.layout.picture_rv_item,parent,false);

        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder)holder;
      //  System.out.println("lllllllllllll"+mReUrl.getUrl().get(position));
        Glide.with(mContext).load(mReUrl.getUrl().get(position)).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(myHolder.mImageView);


    }
   public  void upData(ReUrl reUrl){
       this.mReUrl = reUrl;
       notifyDataSetChanged();

   }

    @Override
    public int getItemCount() {
        return mReUrl.getUrl().size();
    }
    private class  MyHolder extends RecyclerView.ViewHolder {
        private DoubleScaleImageView mImageView;


        public MyHolder(View itemView) {
            super(itemView);
            mImageView = (DoubleScaleImageView) itemView.findViewById(R.id.pic_rv_item);


        }

    }
}

package com.android.newsapplication;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<NewsData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextViewTitle;
        public TextView mTextViewContent;
        public SimpleDraweeView mImageView;

        public MyViewHolder(View v) {
            super(v);
            mTextViewTitle = v.findViewById(R.id.textViewTitle);
            mTextViewContent = v.findViewById(R.id.textViewContent);

            mImageView = v.findViewById(R.id.imageViewTitle);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<NewsData> myDataset, Context context) {
        mDataset = myDataset;
        Fresco.initialize(context);
//        Log.d("종찬", String.valueOf(mDataset.length));
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_news, parent, false);
        //Activity안에 들어갈 메인
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // 표시하는 메소드
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mTextViewTitle.setText(mDataset[position]);
        NewsData news = mDataset.get(position);


        holder.mTextViewTitle.setText(news.getTitle());
        holder.mTextViewTitle.setText(news.getContent());


        Uri uri = Uri.parse(news.getUrlToImage());

        holder.mImageView.setImageURI(uri);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
                            //삼항연산
        return mDataset == null ? 0 : mDataset.size();
    }
}

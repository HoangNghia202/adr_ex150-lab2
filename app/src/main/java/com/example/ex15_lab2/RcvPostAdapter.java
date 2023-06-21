package com.example.ex15_lab2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RcvPostAdapter extends RecyclerView.Adapter<RcvPostAdapter.ViewHolder>{
    private ArrayList<Post> postList;
//    private Context context;

    public RcvPostAdapter(ArrayList<Post> postList) {
        this.postList = postList;
//        this.context = context;
    }


    @NonNull
    @Override
    public RcvPostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvPostAdapter.ViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.tvTitle.setText(post.getTitle());
        holder.tvBody.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        if (postList == null) {
            return 0;
        }
        Log.i("Size list", "getItemCount: "+ postList.size());
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvBody;


        public ViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvBody = view.findViewById(R.id.tvBody);
        }
    }
}


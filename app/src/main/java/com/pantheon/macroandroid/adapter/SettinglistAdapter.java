package com.pantheon.macroandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pantheon.macroandroid.R;
import com.pantheon.macroandroid.model.Region;
import com.pantheon.macroandroid.ui.PublicationsListActivity;

import java.util.List;

public class SettinglistAdapter extends RecyclerView.Adapter<SettinglistAdapter.MyViewHolder> {

    private Context mContext;
    private List<Region> albumList;

    public SettinglistAdapter(Context mContext, List<Region> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card2, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Region album = albumList.get(position);

        holder.selectregioncardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1;
                holder.title.setText(album.getName());
            if (    holder.sw.isChecked()) {
                str1 = holder.sw.getTextOn().toString();
            }
            else{
                str1 = holder.sw.getTextOff().toString();

        }
                Toast.makeText(mContext, ""+holder.title+"\t\t"+position+"\t"+str1
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        LinearLayout selectregioncardView;
        Switch sw;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            sw=view.findViewById(R.id.mySwitchId);


            selectregioncardView = (LinearLayout) view.findViewById(R.id.relative);
        }
    }
}
package com.pantheon.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pantheon.android.R;
import com.pantheon.android.model.Region;
import com.pantheon.android.ui.PublicationsListActivity;

import java.util.List;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.MyViewHolder> {

    private Context mContext;
    private List<Region> albumList;

    public RegionAdapter(Context mContext, List<Region> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Region album = albumList.get(position);
        holder.title.setText(album.getName());

        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PublicationsListActivity.class);
                intent.putExtra("catID", album.getType());
                mContext.startActivity(intent);
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

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            selectregioncardView = (LinearLayout) view.findViewById(R.id.relative);
        }
    }
}
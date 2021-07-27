package com.pantheon.macroandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pantheon.macroandroid.R;
import com.pantheon.macroandroid.model.Region;

import java.util.List;

public class AlertSettingsadapter extends RecyclerView.Adapter<AlertSettingsadapter.MyViewHolder> {

    private Context mContext;
    private List<Region> albumList;

    public AlertSettingsadapter(Context mContext, List<Region> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card3, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Region album = albumList.get(position);
        holder.title.setText(album.getTitle());
        holder.desc.setText(album.getDesc());
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;
        public ImageView thumbnail, overflow;
        LinearLayout selectregioncardView;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            desc =  view.findViewById(R.id.desc);
            selectregioncardView = (LinearLayout) view.findViewById(R.id.relative);
        }
    }
}
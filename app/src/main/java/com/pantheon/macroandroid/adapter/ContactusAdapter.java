package com.pantheon.macroandroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pantheon.macroandroid.R;
import com.pantheon.macroandroid.model.Region;
import com.pantheon.macroandroid.ui.ContactusActivity;

import java.util.List;

public class ContactusAdapter extends RecyclerView.Adapter<ContactusAdapter.MyViewHolder> {

    private Context mContext;
    private List<Region> albumList;

    public ContactusAdapter(Context mContext, List<Region> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_card1, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Region album = albumList.get(position);
        holder.title.setText(album.getContactname());
        holder.contactpost.setText(album.getContactpost());
        holder.contactemail.setText(album.getContactemail());
        holder.contactno.setText(album.getContactno());
        holder.contacttweet.setText(album.getContacttweet());
        Glide.with(mContext).load(album.getContactimg()).into(holder.thumbnail);
        holder.backgroundView.setBackgroundColor(Color.parseColor(album.getBgcolor()));
        holder.backgroundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.title.getText().toString().equalsIgnoreCase("Ian Shepherdson")) {
                    ((ContactusActivity) mContext).ianShepherdson();
                } else if (holder.title.getText().toString().equalsIgnoreCase("Claus Vistesen")) {
                    ((ContactusActivity) mContext).clausVistesen();
                } else if (holder.title.getText().toString().equalsIgnoreCase("Andres Abadia")) {
                    ((ContactusActivity) mContext).andresAbadia();
                } else if (holder.title.getText().toString().equalsIgnoreCase("Freya Beamish")) {
                    ((ContactusActivity) mContext).freyaBemish();
                } else if (holder.title.getText().toString().equalsIgnoreCase("Samuel Tombs")) {
                    ((ContactusActivity) mContext).samuelTombs();
                } else if (holder.title.getText().toString().equalsIgnoreCase("Miguel Chanco")) {
                    ((ContactusActivity) mContext).MiguelChanco();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, contactpost, contactno, contactemail, contacttweet;
        public ImageView thumbnail, overflow;
        LinearLayout backgroundView;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.contacttitle);
            thumbnail = view.findViewById(R.id.thumbnail);
            contactpost = view.findViewById(R.id.subtext);
            contactno = view.findViewById(R.id.contactno);
            contactemail = view.findViewById(R.id.contactemail);
            contacttweet = view.findViewById(R.id.contacttweet);
            backgroundView = view.findViewById(R.id.llView);
        }
    }
}
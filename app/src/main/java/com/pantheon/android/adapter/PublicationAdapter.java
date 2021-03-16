package com.pantheon.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pantheon.android.R;
import com.pantheon.android.bean.PublicationData;
import com.pantheon.android.ui.PublicationArticleActivity;

import java.util.ArrayList;

public class PublicationAdapter extends BaseAdapter {
    Context context;
    private TextView tvArticleHeading, tvArticleAuthor, tvArticleBrief, tvCategory, tvDownloadInfo;
    private LinearLayout llList;
    private RelativeLayout rlArticleFavourite;
    private ImageView ivFavourite, ivNext;
    private ArrayList<PublicationData> publicationList;
    private String downloadstatus;

    public PublicationAdapter(Context context, ArrayList<PublicationData> publicationList) {
        super();
        this.context = context;
        this.publicationList = publicationList;
    }

    @Override
    public int getCount() {
        return publicationList.size();
    }

    @Override
    public Object getItem(int position) {
        return publicationList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return publicationList.indexOf(getItem(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        PublicationData publicationData = publicationList.get(position);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.inflate_publicationlayout, null);
        }

        tvArticleHeading = (TextView) convertView.findViewById(R.id.tvArticleHeading);
        tvArticleAuthor = (TextView) convertView.findViewById(R.id.tvArticleAuthor);
        tvArticleBrief = (TextView) convertView.findViewById(R.id.tvArticleBrief);
        tvCategory = (TextView) convertView.findViewById(R.id.tvCategory);
        tvDownloadInfo = (TextView) convertView.findViewById(R.id.tvDownloadInfo);
        rlArticleFavourite = (RelativeLayout) convertView.findViewById(R.id.rlArticleFavourite);
        ivFavourite = (ImageView) convertView.findViewById(R.id.ivFavourite);
        tvArticleHeading.setText(Html.fromHtml(publicationData.getTitle()));
        tvArticleAuthor.setText(publicationData.getAuthor());
        tvArticleBrief.setText(Html.fromHtml(publicationData.getPreview()));
        tvCategory.setText(publicationData.getCategory());
        tvDownloadInfo.setText(publicationData.getDownloadinfo());
        downloadstatus = publicationData.getDownloadstatus();
        llList = (LinearLayout) convertView.findViewById(R.id.llList);
        ivNext = (ImageView) convertView.findViewById(R.id.ivNext);

        llList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PublicationData publicationData1 = publicationList.get(position);
                Intent intent = new Intent(context, PublicationArticleActivity.class);
                intent.putExtra("article_heading", publicationData1.getTitle().toString());
                intent.putExtra("article_author", publicationData1.getAuthor().toString());
                intent.putExtra("article_preview", publicationData1.getPreview().toString());
                intent.putExtra("article_category", publicationData1.getCategory().toString());
                intent.putExtra("article_downloadinfo", publicationData1.getDownloadinfo().toString());
                intent.putExtra("article_downloadstatus", publicationData1.getDownloadstatus().toString());

                String dtoken = publicationData1.getDownloadtoken().toString();
                System.out.println("Adapter class dtoken...." + dtoken);
                intent.putExtra("article_downloadtoken", dtoken);
                context.startActivity(intent);

            }
        });

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublicationData publicationData1 = publicationList.get(position);

                Intent intent = new Intent(context, PublicationArticleActivity.class);
                intent.putExtra("article_heading", publicationData1.getTitle().toString());
                intent.putExtra("article_author", publicationData1.getAuthor().toString());
                intent.putExtra("article_preview", publicationData1.getPreview().toString());
                intent.putExtra("article_category", publicationData1.getCategory().toString());
                intent.putExtra("article_downloadinfo", publicationData1.getDownloadinfo().toString());
                intent.putExtra("article_downloadstatus", publicationData1.getDownloadstatus().toString());
                //System.out.println("download status check......"+publicationData1.getDownloadstatus().toString());
                intent.putExtra("article_downloadtoken", publicationData1.getDownloadtoken().toString());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}

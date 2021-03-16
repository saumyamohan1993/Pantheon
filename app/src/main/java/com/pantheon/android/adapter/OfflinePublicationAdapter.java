package com.pantheon.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pantheon.android.R;
import com.pantheon.android.bean.PublicationData;
import com.pantheon.android.ui.OfflinePublicationListActivity;

import java.util.ArrayList;

public class OfflinePublicationAdapter extends BaseAdapter {
    Context context;
    private TextView tvArticleHeading, tvArticleAuthor, tvArticleBrief, tvCategory, tvDownloadInfo;
    private LinearLayout llList;
    private RelativeLayout rlArticleFavourite;
    private ImageView ivFavourite, ivNext;
    private Button btnDelete;
    private ArrayList<PublicationData> publicationList;
    private String downloadstatus;
    private ListView lvPublication;

    public OfflinePublicationAdapter(Context context, ArrayList<PublicationData> publicationList, ListView lvPublication) {
        super();
        this.context = context;
        this.publicationList = publicationList;
        this.lvPublication = lvPublication;
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
        final PublicationData publicationData = publicationList.get(position);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.inflate_offlinepublicationlayout, null);
        }

        tvArticleHeading = (TextView) convertView.findViewById(R.id.tvArticleHeading);
        tvArticleAuthor = (TextView) convertView.findViewById(R.id.tvArticleAuthor);
        tvArticleBrief = (TextView) convertView.findViewById(R.id.tvArticleBrief);
        tvCategory = (TextView) convertView.findViewById(R.id.tvCategory);
        tvDownloadInfo = (TextView) convertView.findViewById(R.id.tvDownloadInfo);
        rlArticleFavourite = (RelativeLayout) convertView.findViewById(R.id.rlArticleFavourite);
        ivFavourite = (ImageView) convertView.findViewById(R.id.ivFavourite);
        btnDelete = (Button) convertView.findViewById(R.id.btnDelete);
        tvArticleHeading.setText(Html.fromHtml(publicationData.getTitle()));
        tvArticleAuthor.setText(publicationData.getAuthor());
        //tvArticleBrief.setText(Html.fromHtml(publicationData.getPreview()));
        tvCategory.setText(publicationData.getCategory());
        tvDownloadInfo.setText(publicationData.getDownloadinfo());
        downloadstatus = publicationData.getDownloadstatus();

        llList = (LinearLayout) convertView.findViewById(R.id.llList);
        ivNext = (ImageView) convertView.findViewById(R.id.ivNext);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((OfflinePublicationListActivity) context).deletePublication(publicationData.getId());
            }
        });

        final String info_download = tvDownloadInfo.getText().toString();

        tvArticleHeading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OfflinePublicationListActivity) context).downloadOfflineurl(info_download);
            }
        });

        return convertView;
    }
}

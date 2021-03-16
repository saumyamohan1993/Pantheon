/**
 * @Page/Module Name/Class	:	DataBaseHelper
 * @Author Name        :	Mr. Sombir Singh Bisht
 * @Date                :	Sept 10,  2015
 * @Purpose            :	This page/functionality is used to provide Database.
 */
package com.pantheon.android.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pantheon.android.bean.PublicationData;

import java.util.ArrayList;

/**
 * Created by sombirbisht on 10/9/15.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Articledata.db";
    public static final String TABLE_NAME = "Articles";
    public static final String ARTICLE_HEADING = "heading";
    public static final String DOWNLOAD_INFO = "info";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("DB created", "yes");
        db.execSQL("create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " + ARTICLE_HEADING + " text, " + DOWNLOAD_INFO + " text) ");
        System.out.println("Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int unique(String ARTICLE_HEADING) {

        System.out.println("4......" + ARTICLE_HEADING);
        int i = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String[] coloums = {"id"};
            Cursor res = db.query(TABLE_NAME, coloums, "heading=?", new String[]{ARTICLE_HEADING}, null, null, null);
            i = res.getCount();
            Log.v("count....", "" + i);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public boolean insertRecord(String heading, String info) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ARTICLE_HEADING, heading);
        System.out.println("3....." + heading);
        contentValues.put(DOWNLOAD_INFO, info);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public int deleteRow(int id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            int i = db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});

            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void getAllRecords(ArrayList<PublicationData> list) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.query(TABLE_NAME, null, null, null, null, null, "id DESC");
        System.out.println("Row count :" + res.getCount());
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            PublicationData publicationData = new PublicationData();
            System.out.print("delete position...." + res.getInt(res.getColumnIndex("id")));
            publicationData.setId(res.getInt(res.getColumnIndex("id")));
            publicationData.setTitle(res.getString(res.getColumnIndex(ARTICLE_HEADING)));
            publicationData.setDownloadinfo(res.getString(res.getColumnIndex(DOWNLOAD_INFO)));
            list.add(publicationData);
            res.moveToNext();
        }
    }
}

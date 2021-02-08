/**
 @Page/Module Name/Class	:	DataBaseHelper
 @Author Name		:	Mr. Sombir Singh Bisht
 @Date				:	Sept 10,  2015
 @Purpose			:	This page/functionality is used to provide Database.
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
//    public static final String ARTICLE_AUTHOR = "author";
//    public static final String ARTICLE_PREVIEW = "preview";
//    public static final String ARTICLE_CATEGORY = "category";
    public static final String DOWNLOAD_INFO = "info";
//    public static final String DOWNLOAD_STATUS = "status";


    //constructor for database class DBHelper....
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub.
        Log.e("DB created", "yes");
        //db.execSQL("create table " + TABLE_NAME + " (id INTEGER AUTOINCREMENT, "+ARTICLE_HEADING+" text, "+ARTICLE_AUTHOR+" text, "+ARTICLE_PREVIEW+" text, "+ARTICLE_CATEGORY+" text, "+DOWNLOAD_INFO+" text, "+DOWNLOAD_STATUS+" text, PRIMARY KEY(id) ) ");
        //db.execSQL("create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+ARTICLE_HEADING+" text, "+ARTICLE_AUTHOR+" text, "+ARTICLE_PREVIEW+" text, "+ARTICLE_CATEGORY+" text, "+DOWNLOAD_INFO+" text, "+DOWNLOAD_STATUS+" text) ");
        db.execSQL("create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+ARTICLE_HEADING+" text, "+DOWNLOAD_INFO+" text) ");

        System.out.println("Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

//    public void deleteTable(SQLiteDatabase db) {
//        db.execSQL("delete from " + TABLE_NAME);
//    }
    //String head = Html.fromHtml(ARTICLE_HEADING).toString();
    public int unique(String ARTICLE_HEADING) {
        //String head = Html.fromHtml(ARTICLE_HEADING).toString();
        System.out.println("4......"+ARTICLE_HEADING);

        int i = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            //String selectQuery = "SELECT heading FROM " + TABLE_NAME + " WHERE heading=?";
            String[] coloums={"id"};
            Cursor res = db.query(TABLE_NAME, coloums, "heading=?", new String[]{ARTICLE_HEADING}, null, null, null);
            i = res.getCount();
            Log.v("count....",""+i);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public boolean insertRecord(String heading, String info) {
        Log.e("DB insert call", "yes");

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ARTICLE_HEADING, heading);
        System.out.println("3....."+heading);
//        contentValues.put(ARTICLE_AUTHOR, author);
//        contentValues.put(ARTICLE_PREVIEW, preview);
//        contentValues.put(ARTICLE_CATEGORY, category);
        contentValues.put(DOWNLOAD_INFO, info);
//        contentValues.put(DOWNLOAD_STATUS, status);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    //---deletes a particular title---
    public int deleteRow(int id)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            int i=db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
            //System.out.println("position"+ i);
            return i;
        }
        catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void getAllRecords(ArrayList <PublicationData> list) {
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        Cursor res = db.query(TABLE_NAME,null,null,null, null, null, "id DESC");

        //Cursor res = db.query(TABLE_NAME,"heading",null,"DISTINCT", null, null, null);

        System.out.println("Row count :"+res.getCount());
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            PublicationData publicationData = new PublicationData();
            System.out.print("delete position...." + res.getInt(res.getColumnIndex("id")));
            publicationData.setId(res.getInt(res.getColumnIndex("id")));
            publicationData.setTitle(res.getString(res.getColumnIndex(ARTICLE_HEADING)));
            //System.out.print("heading value...." + res.getInt(res.getColumnIndex(ARTICLE_HEADING)));
//            publicationData.setAuthor(res.getString(res.getColumnIndex(ARTICLE_AUTHOR)));
//            publicationData.setPreview(res.getString(res.getColumnIndex(ARTICLE_PREVIEW)));
//            publicationData.setCategory(res.getString(res.getColumnIndex(ARTICLE_CATEGORY)));
            publicationData.setDownloadinfo(res.getString(res.getColumnIndex(DOWNLOAD_INFO)));
//            publicationData.setDownloadstatus(res.getString(res.getColumnIndex(DOWNLOAD_STATUS)));

            list.add(publicationData);
            res.moveToNext();
        }
    }
}

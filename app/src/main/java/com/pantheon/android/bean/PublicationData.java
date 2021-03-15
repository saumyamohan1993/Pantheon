package com.pantheon.android.bean;

public class PublicationData {

    private int id;
    private String title;
    private String author;
    private String preview;
    private String category;
    private String downloadinfo;
    private String downloadstatus;
    private String downloadtoken;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDownloadstatus() {
        return downloadstatus;
    }

    public void setDownloadstatus(String downloadstatus) {
        this.downloadstatus = downloadstatus;
    }

    public PublicationData(){}  ;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDownloadinfo() {
        return downloadinfo;
    }

    public void setDownloadinfo(String downloadinfo) {
        this.downloadinfo = downloadinfo;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDownloadtoken() {
        return downloadtoken;
    }

    public void setDownloadtoken(String downloadtoken) {
        this.downloadtoken = downloadtoken;
    }


    private static PublicationData rd;
    public static synchronized PublicationData getInstance(){
        if(rd==null){
            rd=new PublicationData();
        }
        return rd;
    }
}

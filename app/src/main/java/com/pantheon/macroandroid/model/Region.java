package com.pantheon.macroandroid.model;

public class Region {
    public String contactname, contactpost, contactno, contactemail, contacttweet, bgcolor;
    private String name;
    private String type;
    private int thumbnail, contactimg;
    public String title,desc;

    public Region(String name, int thumbnail, String type) {
        this.name = name;
        this.type = type;
        this.thumbnail = thumbnail;
    }
    public Region(String name) {
        this.name = name;

    }
    public Region(String title,String desc) {
        this.title = title;
        this.desc=desc;


    }
    public Region(String contactname, String contactpost, String contactno, String contactemail, String contacttweet, int contactimg, String bgcolor) {
        this.contactname = contactname;
        this.contactpost = contactpost;
        this.contactno = contactno;
        this.contactemail = contactemail;
        this.contacttweet = contacttweet;
        this.contactimg = contactimg;
        this.bgcolor = bgcolor;
    }

    public String getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public String getContactemail() {
        return contactemail;
    }

    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
    }

    public int getContactimg() {
        return contactimg;
    }

    public void setContactimg(int contactimg) {
        this.contactimg = contactimg;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getContactpost() {
        return contactpost;
    }

    public void setContactpost(String contactpost) {
        this.contactpost = contactpost;
    }

    public String getContacttweet() {
        return contacttweet;
    }

    public void setContacttweet(String contacttweet) {
        this.contacttweet = contacttweet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

}

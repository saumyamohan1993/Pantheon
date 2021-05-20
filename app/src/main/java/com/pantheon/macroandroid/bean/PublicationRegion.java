package com.pantheon.macroandroid.bean;

import java.util.ArrayList;

public class PublicationRegion extends BaseBean {

    String AppToken;
    boolean ModPubList;
    String source;
    String UName;
    String UId;
    String UToken;
    String catID;
    String Date;
    String Srch;
    String Tags;
    String Custom1;
    String group;
    boolean isProgressEnabled = true;
    private ArrayList<PublicationData> publicationDataArrayList = null;

    public PublicationRegion(String url) {
        this.URL = url;
    }

    public String getUToken() {
        return UToken;
    }

    public void setUToken(String UToken) {
        this.UToken = UToken;
    }

    public String getUId() {
        return UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getCustom1() {
        return Custom1;
    }

    public void setCustom1(String custom1) {
        Custom1 = custom1;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getSrch() {
        return Srch;
    }

    public void setSrch(String srch) {
        Srch = srch;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public String getAppToken() {
        return AppToken;
    }

    public void setAppToken(String appToken) {
        AppToken = appToken;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public boolean isModPubList() {
        return ModPubList;
    }

    public void setModPubList(boolean modPubList) {
        ModPubList = modPubList;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isProgressEnabled() {
        return isProgressEnabled;
    }

    public void setIsProgressEnabled(boolean isProgressEnabled) {
        this.isProgressEnabled = isProgressEnabled;
    }

    public ArrayList<PublicationData> getPublicationDataArrayList() {
        return publicationDataArrayList;
    }

    public void setPublicationDataArrayList(ArrayList<PublicationData> publicationDataArrayList) {
        this.publicationDataArrayList = publicationDataArrayList;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}

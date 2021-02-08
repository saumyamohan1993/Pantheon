/**
 @Module Name/Class		:	Login
 @Author Name			:	Sombir Singh Bisht
 @Date					:	Aug 27, 2015
 @Purpose				:	This class is used to save Login Data.
 */
package com.pantheon.android.bean;

/**
 * Created by sombirbisht on 27/8/15.
 */
public class Login extends BaseBean {
    String  appToken;
    boolean doLogin;
    String  source;
    String  uname=null;
    String  upass=null;

    String  uid=null;
    String  uToken=null;
    String  ufname=null;
    String  ulname=null;

    public Login(String url){
        this.URL = url;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public boolean isDoLogin() {
        return doLogin;
    }

    public void setDoLogin(Boolean doLogin) {
        this.doLogin = doLogin;
    }

    public String getUlname() {
        return ulname;
    }

    public void setUlname(String ulname) {
        this.ulname = ulname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getuToken() {
        return uToken;
    }

    public void setuToken(String uToken) {
        this.uToken = uToken;
    }

    public String getUfname() {
        return ufname;
    }

    public void setUfname(String ufname) {
        this.ufname = ufname;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public boolean isProgressEnabled() {
        return isProgressEnabled;
    }

    public void setIsProgressEnabled(boolean isProgressEnabled) {
        this.isProgressEnabled = isProgressEnabled;
    }

    boolean isProgressEnabled=true;

}

/**
 @Module Name/Class		:	Reset
 @Author Name			:	Sombir Singh Bisht
 @Date					:   Sept 11, 2015
 @Purpose				:	This class is used to save Forgot Password Data.
 */
package com.pantheon.android.bean;

/**
 * Created by sombirbisht on 11/9/15.
 */
public class Reset extends BaseBean{
    String apptoken;
    boolean doreset;
    String source;
    String  uname=null;

    public Reset(String url){
        this.URL = url;
    }

    public String getApptoken() {
        return apptoken;
    }

    public void setApptoken(String apptoken) {
        this.apptoken = apptoken;
    }

    public boolean isDoreset() {
        return doreset;
    }

    public void setDoreset(boolean doreset) {
        this.doreset = doreset;
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
}

package com.pantheon.macroandroid.http;

import com.pantheon.macroandroid.utility.App;
public abstract class HttpConstant {
    //Development server
   // public final static String DEVELOPMENT_SERVER = "http://54.160.13.227/textwheels/index.php/txtwheel_api/";
    //Live server
//    public final static String DEVELOPMENT_SERVER = "https://www.pantheonmacro.com/pro/PRO_api.php";
    public final static String DEVELOPMENT_SERVER = "https://www.pantheonmacro.com/cfe/PRO_documents.php";

    public final static String BASE_URL;
    static {
    	if(App.isDebuggable){
    		BASE_URL = DEVELOPMENT_SERVER;
    	} else {
    		BASE_URL = DEVELOPMENT_SERVER;
    	}
    }
    public static final String LOGIN_URL = BASE_URL ;
    public static final String USMONITOR_URL = BASE_URL;
    public static final String RESET_URL = BASE_URL;

}

package com.pantheon.macroandroid.http;

import android.content.Context;

import com.pantheon.macroandroid.bean.BaseBean;
import com.pantheon.macroandroid.utility.App;
import com.pantheon.macroandroid.utility.AppUtility;

public class HttpConnectionUtil {
    private static final String TAG = "HttpConnectionUtil";

    public static void callWebService(BaseBean baseBean, Context context, WebserviceType type, BaseListener.OnWebServiceCompleteListener<BaseBean> listener) {

        if (AppUtility.isInternetAvailable(context) == false) {
            return;
        }

        try {
            switch (type) {
                case LOGIN:
                    new HttpConnectionTask().callWebService(baseBean, context, type, listener, HttpNameValuePair.getLoginNameValuePair(baseBean));
                    break;

                case USMONITOR:
                    new HttpConnectionTask().callWebService(baseBean, context, type, listener, HttpNameValuePair.getUSMonitorNameValuePair(baseBean));
                    break;

                case SEARCH:
                    new HttpConnectionTask().callWebService(baseBean, context, type, listener, HttpNameValuePair.getUSMonitorNameValuePair(baseBean));
                    break;

                case RESET:
                    new HttpConnectionTask().callWebService(baseBean, context, type, listener, HttpNameValuePair.getResetNameValuePair(baseBean));
                    break;

            }
        } catch (ClassCastException e) {

            if (App.isDebuggable) {
            }
        }
    }
}

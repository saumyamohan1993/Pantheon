package com.pantheon.macroandroid.http;

import android.content.Context;
import android.text.Html;

import com.pantheon.macroandroid.bean.BaseBean;
import com.pantheon.macroandroid.bean.Login;
import com.pantheon.macroandroid.bean.PublicationData;
import com.pantheon.macroandroid.bean.PublicationRegion;
import com.pantheon.macroandroid.bean.Reset;
import com.pantheon.macroandroid.utility.App;
import com.pantheon.macroandroid.utility.SharedPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Http Response class to handle response of web Api's
 */
public class HttpServiceResponse {
    private static final String TAG = "HttpServiceResponse";
    private static final String US = "1174";
    private static final String EURO = "1172";
    private static final String LATAM = "1170";
    private static final String UK = "1561";
    private static final String ASIA_MONITOR = "1863";

    public static BaseBean getResponse(Context context, BaseBean beanObject, WebserviceType type, String result) {
        try {
            switch (type) {

                case LOGIN:
                    return getResponse(context, (Login) beanObject, result);
                case USMONITOR:
                    return getResponse(context, (PublicationRegion) beanObject, result);
                case SEARCH:
                    return getSearchResponse(context, (PublicationRegion) beanObject, result);
                case RESET:
                    return getResponse(context, (Reset) beanObject, result);
            }
        } catch (ClassCastException e) {
            System.out.println("Exception  " + e.getMessage());

        }
        return null;
    }

    private static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ee) {
                return false;
            }
        }
        return true;
    }

    /**
     * Login Response
     *
     * @param context
     * @param beanObject
     * @param result
     * @return
     */
    private static BaseBean getResponse(Context context, Login beanObject, String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);

            beanObject.result = jsonObject.getBoolean("result");
            App.showLog(TAG, "Result:::" + jsonObject.getBoolean("result"));

            if (jsonObject.getBoolean("result") == true) {
                JSONObject object = jsonObject.getJSONObject("UDATA");
                String userId = object.getString("UID");
                System.out.println(userId);
                String userName = object.getString("UNAME");
                System.out.println(userName);
                String userToken = object.getString("UTOKEN");
                System.out.println(userToken);

                SharedPreferenceManager preferenceManager = SharedPreferenceManager.getInstance();
                preferenceManager.setUserId(context, userId);
                preferenceManager.setUserName(context, userName);
                preferenceManager.setUserToken(context, userToken);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return beanObject;
    }

    private static BaseBean getResponse(Context context, PublicationRegion beanObject, String result) {
        try {

            JSONObject jsonObject = new JSONObject(result);
            beanObject.result = jsonObject.getBoolean("result");
            if (jsonObject.getBoolean("result") == true) {

                JSONArray jsonArray = jsonObject.getJSONArray("CDATA");
                ArrayList<PublicationData> publicationDataArrayList = new ArrayList<PublicationData>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    PublicationData publicationData = new PublicationData();
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    publicationData.setTitle(Html.fromHtml(jsonObject1.getString("title")).toString());
                    publicationData.setAuthor(jsonObject1.getString("author"));
                    publicationData.setPreview(Html.fromHtml(jsonObject1.getString("preview")).toString());
                    publicationData.setCategory(jsonObject1.getString("category"));
                    publicationData.setDownloadinfo(jsonObject1.getString("downloadinfo"));
                    publicationData.setDownloadstatus(jsonObject1.getString("downloadstatus"));
                    publicationData.setDownloadtoken(jsonObject1.getString("downloadtoken"));
                    publicationDataArrayList.add(publicationData);
                }

                beanObject.setPublicationDataArrayList(publicationDataArrayList);

            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return beanObject;
    }

    private static BaseBean getSearchResponse(Context context, PublicationRegion beanObject, String result) {
        try {
            App.showLog(TAG, "PublicationResponse .. " + result);
            JSONObject jsonObject = new JSONObject(result);

            App.showLog(TAG, "PublicationNOW " + jsonObject.toString());

            beanObject.result = jsonObject.getBoolean("result");
            if (jsonObject.getBoolean("result") == true) {

                JSONArray jsonArray = jsonObject.getJSONArray("CDATA");
                ArrayList<PublicationData> publicationDataArrayList = new ArrayList<PublicationData>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    PublicationData publicationData = new PublicationData();
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    publicationData.setTitle(Html.fromHtml(jsonObject1.getString("title")).toString());
                    publicationData.setAuthor(jsonObject1.getString("author"));
                    publicationData.setPreview(Html.fromHtml(jsonObject1.getString("preview")).toString());
                    publicationData.setCategory(jsonObject1.getString("category"));
                    publicationData.setDownloadinfo(jsonObject1.getString("downloadinfo"));
                    publicationData.setDownloadstatus(jsonObject1.getString("downloadstatus"));
                    publicationData.setDownloadtoken(jsonObject1.getString("downloadtoken"));
                    publicationDataArrayList.add(publicationData);

                }

                beanObject.setPublicationDataArrayList(publicationDataArrayList);

            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return beanObject;
    }

    private static BaseBean getResponse(Context context, Reset beanObject, String result) {
        try {

            System.out.println("Forgot....." + result);
            JSONObject jsonObject = new JSONObject(result);
            System.out.println("Forgot Result...." + result);
            App.showLog(TAG, "reset  .. " + result);
            beanObject.result = jsonObject.getBoolean("result");
            App.showLog(TAG, "Result:::" + jsonObject.getBoolean("result"));

            if (jsonObject.getBoolean("result") == true) {

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return beanObject;
    }

}

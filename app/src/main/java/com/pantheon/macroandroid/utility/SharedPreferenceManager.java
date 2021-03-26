/**
 * @Page/Module Name/Class	:	SharedPreferenceManager
 * @Author Name        :	Mr. Sombir Singh Bisht
 * @Date :	Aug 25,  2015
 * @Purpose :	This page/functionality is used to provide Local Database.
 */
package com.pantheon.macroandroid.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private static final String PREFS_INFO = "prefs_info";
    private static final String REM_INFO = "rem_info";

    private SharedPreferenceManager() {
    }

    private static SharedPreferenceManager manager;

    public static SharedPreferenceManager getInstance() {
        if (manager == null) {
            manager = new SharedPreferenceManager();
        }
        return manager;
    }

    public SharedPreferences getSharedPrefernces(Context context) {
        return context.getSharedPreferences(PREFS_INFO, 0);
    }

    public static void clearCredentials(Context context) {
        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(REM_INFO, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    public void setLoginCheck(Context context, Boolean check) {

        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(REM_INFO, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("check", check);
        editor.commit();
    }

    public static boolean getLoginCheck(Context context) {

        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(REM_INFO, 0);
        return preferences.getBoolean("check", false);
    }

    public void setRememberStatus(Context context, Boolean remember) {

        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(PREFS_INFO, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("remember", remember);
        editor.commit();
    }

    public static Boolean getRememberStatus(Context context) {

        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(PREFS_INFO, 0);
        return preferences.getBoolean("remember", false);
    }

    public void setEmailStatus(Context context, String email) {

        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(PREFS_INFO, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("mailid", email);
        editor.commit();
    }

    public static String getEmailStatus(Context context) {
        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(PREFS_INFO, 0);
        return preferences.getString("mailid", null);
    }

    public void setPasswordStatus(Context context, String password) {
        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(PREFS_INFO, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("pass", password);
        editor.commit();
    }

    public static String getPasswordStatus(Context context) {
        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(PREFS_INFO, 0);
        return preferences.getString("pass", null);
    }

    public void setUserId(Context context, String id) {

        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(REM_INFO, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("id", id);
        editor.commit();
    }

    public static String getUserId(Context context) {

        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(REM_INFO, 0);
        return preferences.getString("id", null);
    }

    public void setUserName(Context context, String name) {

        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(REM_INFO, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.commit();
    }

    public static String getUserName(Context context) {

        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(REM_INFO, 0);
        return preferences.getString("name", null);
    }

    public void setUserToken(Context context, String token) {

        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(REM_INFO, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.commit();
    }

    public String getUserToken(Context context) {

        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(REM_INFO, 0);
        return preferences.getString("token", null);
    }

    public void setUserEmail(Context context, String useremail) {

        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(REM_INFO, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("useremail", useremail);
        editor.commit();
    }

    public String getUserEmail(Context context) {
        SharedPreferences preferences = (SharedPreferences) context.getSharedPreferences(REM_INFO, 0);
        return preferences.getString("useremail", null);
    }

}

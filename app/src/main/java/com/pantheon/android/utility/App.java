package com.pantheon.android.utility;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

public class App extends Application {
public static Bitmap imageEditingBitmap;
    public static final boolean APP_TAG=true;
	private static App mInstance;
	public static boolean isDebuggable;
	
	public Bitmap appBackgroundBitmap;
	public Bitmap appOtherUserBackgroundBitmap;

	@Override
	public void onCreate() {
        super.onCreate();
        mInstance = this;
        isDebuggable = AppUtility.isDebuggable();
    }

    /*
     * Show Application Log Message
     */
    public static void showLog(String TAG,String message){
        if(APP_TAG){
            if (message==null)
                message="null";
        }
    }

    public static App getInstance(){
		return mInstance;
	}
}

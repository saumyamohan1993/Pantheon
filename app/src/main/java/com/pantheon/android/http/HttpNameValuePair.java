package com.pantheon.android.http;


import android.util.Log;

import com.pantheon.android.bean.BaseBean;
import com.pantheon.android.bean.Login;
import com.pantheon.android.bean.PublicationRegion;
import com.pantheon.android.bean.Reset;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * NameValue pairs
 */
public class HttpNameValuePair {


	public static List<NameValuePair> getLoginNameValuePair(BaseBean baseBean){
		Login login = (Login)baseBean;
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("UNAME", login.getUname()));
		pairs.add(new BasicNameValuePair("UPASS", login.getUpass()));
		pairs.add(new BasicNameValuePair("AppToken", login.getAppToken()));
		pairs.add(new BasicNameValuePair("DoLogin","true"));
		pairs.add(new BasicNameValuePair("source", login.getSource()));
        Log.v("", "Login values " + pairs.toString());
		return pairs;

//        user_email,user_password

	}

	public static List<NameValuePair> getUSMonitorNameValuePair(BaseBean baseBean){
		PublicationRegion publicationRegion = (PublicationRegion)baseBean;
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();

		pairs.add(new BasicNameValuePair("AppToken", publicationRegion.getAppToken()));
		pairs.add(new BasicNameValuePair("ModPubList", "true"));
		pairs.add(new BasicNameValuePair("source", publicationRegion.getSource()));
		//pairs.add(new BasicNameValuePair("catID", publicationRegion.getCatID()));
		pairs.add(new BasicNameValuePair("UNAME", publicationRegion.getUName()));
		pairs.add(new BasicNameValuePair("UID", publicationRegion.getUId()));
		pairs.add(new BasicNameValuePair("UTOKEN", publicationRegion.getUToken()));
		pairs.add(new BasicNameValuePair("catID", publicationRegion.getCatID()));
		pairs.add(new BasicNameValuePair("date", publicationRegion.getDate()));
		pairs.add(new BasicNameValuePair("srch", publicationRegion.getSrch()));
		pairs.add(new BasicNameValuePair("tags", publicationRegion.getTags()));
		pairs.add(new BasicNameValuePair("custom1", publicationRegion.getCustom1()));

		System.out.println("PublicationRegion value ....... " + pairs.toString());

		return pairs;
	}



	public static List<NameValuePair> getResetNameValuePair(BaseBean baseBean){
		Reset reset = (Reset)baseBean;
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();

		pairs.add(new BasicNameValuePair("AppToken", reset.getApptoken()));
		pairs.add(new BasicNameValuePair("DoReset", "true"));
		pairs.add(new BasicNameValuePair("source", reset.getSource()));
		pairs.add(new BasicNameValuePair("UNAME", reset.getUname()));

		System.out.println("reset value ....... " + pairs.toString());

		return pairs;
	}

}

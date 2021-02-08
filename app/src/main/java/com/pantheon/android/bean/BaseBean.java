/**
 @Module Name/Class		:	BaseBean
 @Author Name			:	Sombir Singh Bisht
 @Date					:	Aug 26, 2015
 @Purpose				:	This class is used to save Base Bean data.
 */
package com.pantheon.android.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaseBean implements Serializable {
	public String URL;
	private String progressMsg = "Please wait...";
	private boolean progressEnable;
	
	public int statusCode;
	public String statusMsg;
	public boolean result;

	protected BaseBean(){
	}
	
	protected BaseBean(String url){
		this.URL = url;
	}
	
	public String getProgressMsg(){
		return progressMsg;
	}
	public void setProgressMsg(String progressMsg){
		this.progressMsg = progressMsg;
	}
	
	public boolean isProgressEnable(){
		return progressEnable;
	}
	
	public void setProgressEnable(boolean enbale){
		progressEnable = enbale;
	}
}

package com.pantheon.macroandroid.http;

public interface BaseListener {
	@SuppressWarnings("hiding")
	public interface OnWebServiceCompleteListener<BaseBean> {
		public void onWebServiceComplete(BaseBean baseObject);
	}
}


package com.pantheon.android.http;

public interface BaseListener {
	@SuppressWarnings("hiding")
	public interface OnWebServiceCompleteListener<BaseBean> {
		public void onWebServiceComplete(BaseBean baseObject);
	}
}


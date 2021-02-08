package com.pantheon.android.http;



public interface BaseListener { 

    @SuppressWarnings("hiding")
	    public interface OnWebServiceCompleteListener<BaseBean> {
		public void onWebServiceComplete(BaseBean baseObject);
	}
    
   /* public static interface OnListItemButtonsClickListener {
        public void onListItemButtonClick(int position, ListItemClickedButtonEnum button, Object... baseBeans);
    }*/


} 
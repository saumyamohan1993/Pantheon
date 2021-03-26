package com.pantheon.macroandroid.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.pantheon.macroandroid.bean.BaseBean;
import com.pantheon.macroandroid.utility.App;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class HttpConnectionTask extends AsyncTask<Void, Void, BaseBean> {
    private ProgressDialog mProgressDialog;
    private BaseBean mObject;
    private Context mContext;
    private WebserviceType mType;
    private BaseListener.OnWebServiceCompleteListener<BaseBean> mListener;
    private List<NameValuePair> mNameValuePairs;

    public static HttpClient createHttpClient() {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.DEFAULT_CONTENT_CHARSET);
        HttpProtocolParams.setUseExpectContinue(params, true);

        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);
        return new DefaultHttpClient(conMgr, params);
    }

    public void callWebService(BaseBean bean, Context context, WebserviceType type, BaseListener.OnWebServiceCompleteListener<BaseBean> listener, List<NameValuePair> nameValuePairs) {
        mObject = bean;
        mContext = context;
        mType = type;
        mListener = listener;
        mNameValuePairs = nameValuePairs;
        this.execute();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mObject.isProgressEnable()) {
            this.mProgressDialog = ProgressDialog.show(mContext, null, mObject.getProgressMsg());
            this.mProgressDialog.setCancelable(false);
        }
    }

    @Override
    protected BaseBean doInBackground(Void... params) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(mObject.URL);

        try {
            if (mNameValuePairs != null) {
                httppost.setEntity(new UrlEncodedFormEntity(mNameValuePairs));
            }
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);

                mObject = HttpServiceResponse.getResponse(mContext, mObject, mType, result);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mObject;
    }

    @Override
    protected void onPostExecute(BaseBean result) {
        super.onPostExecute(result);
        if (mObject.isProgressEnable()) {
            this.mProgressDialog.dismiss();
        }
        if (mObject == null) {
            return;
        }
        if (App.isDebuggable && mObject.statusCode == -100) {
        }


        if (mListener != null) {
            mListener.onWebServiceComplete(result);
        }
    }

    private String convertStreamToString(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (inputStream != null) {
            String line;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                inputStream.close();
            }
        }
        return sb.toString();
    }
}

package com.pantheon.android.services;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class WebServiceConnection {

    public static String postData(String url, ArrayList<NameValuePair> params) {
        final DefaultHttpClient client = new DefaultHttpClient();
        HttpPost method = null;
        try {
            method = new HttpPost(new URI(url));
            JSONObject paramObject = new JSONObject();
            System.out.println("Url ::::" + url);
            for (int i = 0; i < params.size(); i++) {
                try {
                    paramObject.put(params.get(i).getName(), params.get(i).getValue());
                    System.out.println(params.get(i).getName() + ":::::" + params.get(i).getValue());
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            StringEntity string = new StringEntity(paramObject.toString());
            string.setContentType("text/json");
            method.setEntity(string);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpResponse res = null;
        try {
            if (client != null)
                res = client.execute(method);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (res != null) {
            try {
                String str = "";
                str = inputStreamToString(res.getEntity().getContent()).toString();
                return str;
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return "";
            } catch (IOException e) {

                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    public static String postJsonObjectToServer(JSONObject jsonObject, String url) {

        System.out.println("Url: " + url);
        System.out.println("JSONOBJECT: " + jsonObject);
        final DefaultHttpClient client = new DefaultHttpClient();
        HttpPost method = null;
        try {
            method = new HttpPost(new URI(url));
            method.setHeader("Content-Type", "application/json");
            method.setEntity(new ByteArrayEntity(jsonObject.toString().getBytes("UTF8")));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpResponse res = null;
        try {
            if (client != null)
                res = client.execute(method);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (res != null) {
            HttpEntity entity = res.getEntity();
            InputStream input = null;
            try {
                input = res.getEntity().getContent();
                byte[] data = new byte[256];
                int len = 0;
                int size = 0;
                StringBuffer raw = new StringBuffer();


                while (-1 != (len = input.read(data))) {

                    raw.append(new String(data, 0, len));
                    size += len;
                }
                input.close();
                return raw.toString();
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return "";
            } catch (IOException e) {

                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

//    public static String postJsonArrayToServer(JSONArray jsonArray, String url)
//    {
//
//        System.out.println("Url: "+url);
//        System.out.println("JSON Array: "+jsonArray);
//        final DefaultHttpClient client = new DefaultHttpClient();
//        HttpPost method=null;
//        try {
//            method = new HttpPost(new URI(url));
//            method.setHeader("Content-Type", "application/json");
//            method.setEntity(new ByteArrayEntity(jsonArray.toString().getBytes("UTF8")));
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        catch (URISyntaxException e)
//        {
//            e.printStackTrace();
//        }
//        HttpResponse res=null;
//        try
//        {
//            if(client!=null)
//                res = client.execute(method);
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if(res!=null)
//        {
//            HttpEntity entity=res.getEntity();
//            InputStream input=null;
//            try {
//                input = res.getEntity().getContent();
//                byte[] data = new byte[256];
//                int len = 0;
//                int size = 0;
//                StringBuffer raw = new StringBuffer();
//
//
//                while ( -1 != (len = input.read(data)) )
//                {
//
//                    raw.append(new String(data, 0, len));
//                    size += len;
//                }
//                input.close();
//                return raw.toString();
//            } catch (IllegalStateException e) {
//                e.printStackTrace();
//                return "";
//            } catch (IOException e) {
//
//                e.printStackTrace();
//                return "";
//            }
//        }
//        else
//        {
//            return "";
//        }
//    }

    public static String postJsonArrayToServer(JSONArray jsonArray, String url) {
        System.out.println("#########################");
        System.out.println("Url: " + url);
        System.out.println("JSON ARRAY: " + jsonArray);
        final DefaultHttpClient client = new DefaultHttpClient();
        HttpPost method = null;
        try {
            method = new HttpPost(new URI(url));
            method.setHeader("Content-Type", "application/json");

            method.setEntity(new ByteArrayEntity(jsonArray.toString().getBytes("UTF8")));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpResponse res = null;
        try {
            if (client != null)
                res = client.execute(method);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (res != null) {
            HttpEntity entity = res.getEntity();
            InputStream input = null;
            try {
                input = res.getEntity().getContent();
                byte[] data = new byte[256];
                int len = 0;
                int size = 0;
                StringBuffer raw = new StringBuffer();

                while (-1 != (len = input.read(data))) {
                    raw.append(new String(data, 0, len));
                    size += len;
                }
                input.close();
                return raw.toString();
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return "";
            } catch (IOException e) {

                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    public Document getDomElement(String xml) {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);
        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        }
        return doc;
    }

    private static StringBuilder inputStreamToString(InputStream is) {
        String line = "";
        StringBuilder total = new StringBuilder();
        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = null;
        try {
            rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // Read response until the end
        try {
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Return full string
        return total;
    }

    public static String GetData(String url) {
        InputStream inputStream = null;
        String result = "";
        try {// create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
            if (inputStream != null)
                result = inputStreamToString(inputStream).toString();
            else
                result = "Did not work!";
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }
}
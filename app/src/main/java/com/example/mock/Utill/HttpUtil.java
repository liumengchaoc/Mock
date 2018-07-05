package com.example.mock.Utill;

import android.content.Context;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class HttpUtil {

    public  static void getdata(final String path, Context context, final JsonBack jsonBack){

            AsyncTask<String,Void,String> asyncTask = new AsyncTask<String, Void, String>() {
                @Override
                protected String doInBackground(String... params) {


                    try {
                        URL url1 = new URL(path);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setReadTimeout(5000);
                        httpURLConnection.setConnectTimeout(5000);

                        if(httpURLConnection.getResponseCode() == 200){
                            //字节流
                            InputStream inputStream = httpURLConnection.getInputStream();


                            String string = SteamString.transfrom(inputStream);

                            return string;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);


                    jsonBack.getdata(s);
                }
            };

            asyncTask.execute();

        }




    }







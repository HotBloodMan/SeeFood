package com.ljt.seefood.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 1 on 2017/3/23.
 */

public class HttpUtils {


    private void initDatas() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String url="http://apis.juhe.cn/cook/query?key=de4510fc06f56addef3e30b222e44270&menu=%E8%A5%BF%E7%BA%A2%E6%9F%BF";
                try {
                    URL urls = new URL(url);

                    HttpURLConnection connection= (HttpURLConnection) urls.openConnection();
                    connection.setDoInput(true);
                    connection.setRequestMethod("GET");
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    //将流转换成字符串
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while((line=br.readLine())!=null){
                        sb.append(line);
                    }
                    String results=sb.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

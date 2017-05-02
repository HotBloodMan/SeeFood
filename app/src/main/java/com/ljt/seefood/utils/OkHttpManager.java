package com.ljt.seefood.utils;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 1 on 2017/3/23.
 */

public class OkHttpManager {
    //提交字符串
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");
    private final OkHttpClient okHttpClient;
    private final Handler handler;
    private volatile  static OkHttpManager mOkHttpManager;


    private OkHttpManager(){
        okHttpClient = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
    }
    public static OkHttpManager getInstance(){
        OkHttpManager instance=null;
        if(mOkHttpManager==null){
            synchronized (OkHttpManager.class){
                instance=new OkHttpManager();
                mOkHttpManager=instance;
            }
        }
        return mOkHttpManager;
    }
    public interface Func1{
        void onResponse(String jsonString);
        void onFailure(IOException exception);
    }

    public void asyncJsonStringByURL(String url,final Func1 callBack){
        Request request = new Request.Builder().url(url).get().build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(callBack!=null){
                            callBack.onFailure(e);
                        }
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    onSuccessResultJsonStringMethod(response.body().string(),callBack);
                }
            }
        });
    }
    //请求返回json字符串
    private void onSuccessResultJsonStringMethod(final String json, final Func1 callback){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(callback!=null){
                    callback.onResponse(json);
                }
            }
        });
    }

}

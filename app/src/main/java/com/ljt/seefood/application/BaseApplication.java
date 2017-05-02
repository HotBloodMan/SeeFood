package com.ljt.seefood.application;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * Created by 1 on 2017/3/8.
 */

public class BaseApplication extends Application{

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
        Fresco.initialize(this);
        x.Ext.init(this);
        mContext=getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}

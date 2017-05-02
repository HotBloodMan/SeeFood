package com.ljt.seefood.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by 1 on 2017/3/24.
 */

public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
        init(context);
    }


    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //设置字体
    private void init(Context context) {
        Log.d("TAG","aaa---->>>init(Context context)");
        AssetManager assets = context.getAssets();
        Typeface font = Typeface.createFromAsset(assets, "fonts/Lobster-1.4.otf");
        setTypeface(font);
    }

}

package com.ljt.seefood.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.ljt.seefood.R;
import com.zt.jackone.AppConnect;

public class NewsDetailActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initView();
    }
    private void initView() {
        LinearLayout adLayout= (LinearLayout) findViewById(R.id.AdLinearLayout);
        AppConnect.getInstance(this).showBannerAd(this,adLayout);

        webView = (WebView) findViewById(R.id.webView_news_detail);
        String url = getIntent().getExtras().getString("url");
        webView.loadUrl(url);
    }
}

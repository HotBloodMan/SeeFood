package com.ljt.seefood.newsdata;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ljt.seefood.adapter.ListViewAdapter;
import com.ljt.seefood.entity.News;
import com.ljt.seefood.ui.NewsDetailActivity;
import com.ljt.seefood.utils.OkHttpManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 1 on 2017/3/23.
 */

public class NewsData {
    private String category;
    private Context context;
    private ProgressBar progressBar;
    private RelativeLayout relativeLayout;
    private Button updateBut;
    private PullToRefreshListView pullToRefreshListView;


    public NewsData(final Context context, String top, ProgressBar progressBar, RelativeLayout relativeLayout, Button updateBut, PullToRefreshListView pullToRefreshListView) {
        this.context=context;
        this.category=top;
        this.progressBar=progressBar;
        this.relativeLayout=relativeLayout;
        this.updateBut=updateBut;
        this.pullToRefreshListView=pullToRefreshListView;

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                getData();
            }
        });
        //
        ILoadingLayout proxy = pullToRefreshListView.getLoadingLayoutProxy(true, false);
        proxy.setRefreshingLabel("正在玩命加载中...");

    }

    public void getData() {
        String url = "http://v.juhe.cn/toutiao/index?type="+category+"&key=aa86168aa79302580d8a91e57a0f9400";
        OkHttpManager.getInstance().asyncJsonStringByURL(url,new OkHttpManager.Func1(){

            @Override
            public void onResponse(String jsonString) {
                if(jsonString!=null){
                    ArrayList<News> list = parseData(jsonString);
                    showDataInUI(category,list);
                }
            }

            @Override
            public void onFailure(IOException exception) {
                progressBar.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);//显示网络链接
                updateBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getData(); //从新获取该类型的数据
                    }
                });

                if (pullToRefreshListView.isShown()){
                    pullToRefreshListView.onRefreshComplete();//关闭试图
                }
            }
        });
    }

    private void showDataInUI(String category, ArrayList<News> list) {
        progressBar.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.GONE);

        final ListViewAdapter adapter = new ListViewAdapter(context, list);
        pullToRefreshListView.setAdapter(adapter);

        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = (News) adapter.getItem(position - 1);
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("url", news.getUrl());
                context.startActivity(intent);

            }
        });
        if(pullToRefreshListView.isShown()){
            pullToRefreshListView.onRefreshComplete();
        }
    }

    //解析json
    protected ArrayList<News> parseData(String json) {
        try {
            if(json==null){
                return null;
            }
            JSONObject jsonObject = new JSONObject(json);
            JSONObject rs = jsonObject.getJSONObject("result");
            JSONArray jsonArray = rs.getJSONArray("data");
            ArrayList<News> newsDataList = new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                News news = new News();
                news.authorName = jsonObj.getString("author_name");
                news.updateTime = jsonObj.getString("date");
                news.title = jsonObj.getString("title");
                news.url = jsonObj.getString("url");
                if(jsonObj.has("thumbnail_pic_s")){
                    news.picUrl = jsonObj.getString("thumbnail_pic_s");
                }
                newsDataList.add(news);
            }
            return newsDataList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

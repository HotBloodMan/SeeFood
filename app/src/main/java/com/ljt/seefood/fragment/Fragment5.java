package com.ljt.seefood.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ljt.seefood.R;
import com.ljt.seefood.newsdata.NewsData;

/**
 * Created by Administrator on 2016/9/9.
 * 单列设计模式：懒汉式 3
 */
public class Fragment5 extends Fragment {
    private static Fragment5 instance;
    private  static View view;
    public Fragment5(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //防止多次初始化布局,布局也设置为单列模式的
        if (view==null) {
            view = inflater.inflate(R.layout.fragment_listview, container, false);
            initView();
            return view;
        }else {
            return view;
        }
    }
    public static Fragment5 genInstance(){
        if (instance==null) {
            instance = new Fragment5();
            return instance;
        }
        return instance;
    }
    private ProgressBar progressBar;
    private RelativeLayout relativeLayout;
    private Button updateBut;
    private PullToRefreshListView pullToRefreshListView;
    private void initView() {
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.update_relateLayout);
        updateBut = (Button) view.findViewById(R.id.but_update);
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pulltorefresh);

        NewsData newsDate = new NewsData(getActivity(),"keji",progressBar,relativeLayout,updateBut,pullToRefreshListView);
        newsDate.getData();
    }
}

package com.ljt.seefood.fragment;


import android.os.Bundle;
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
 * A simple {@link Fragment} subclass.
 */
public class FragmentH1 extends Fragment {

    private static FragmentH1 instanceFH1;
    private ProgressBar progressBar;
    private RelativeLayout relativeLayout;
    private Button updateBut;
    private PullToRefreshListView pullToRefreshListView;

    public FragmentH1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_h1, container, false);
        initWidget(view);
        return view;
    }

    private void initWidget(View view) {
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.update_relateLayout);
        updateBut = (Button) view.findViewById(R.id.but_update);
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pulltorefresh);

        NewsData newsData = new NewsData(getActivity(), "top", progressBar, relativeLayout, updateBut, pullToRefreshListView);
        newsData.getData();

    }

    public static FragmentH1 getInstance(){
        if(instanceFH1==null){
            instanceFH1=new FragmentH1();
        }
        return instanceFH1;
    }
}

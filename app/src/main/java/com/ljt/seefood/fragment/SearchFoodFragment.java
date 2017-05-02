package com.ljt.seefood.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljt.seefood.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFoodFragment extends Fragment {

    //标题
    private String [] TILTE=new String[]{"周排行","月排行","总排行"};
    @Bind(R.id.hot_viewpager)
    ViewPager videoViewPager;
    @Bind(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    private List<Fragment> fragments=new ArrayList<>();

    public SearchFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_food, container, false);
        ButterKnife.bind(this,view);
        initView();
        initData();
        setAdapter();
        return view;

    }
    //设置适配器
    private void setAdapter() {
       //实例化适配器
        FragmentPagerItemAdapter adapter=new FragmentPagerItemAdapter(getFragmentManager(),
                FragmentPagerItems.with(getContext())
                .add(TILTE[0],fragments.get(0).getClass())
                .add(TILTE[1],fragments.get(1).getClass())
                .add(TILTE[2],fragments.get(2).getClass())
                .create());
        //设置适配器
        videoViewPager.setAdapter(adapter);
        viewpagertab.setViewPager(videoViewPager);
    }

    private void initData() {
        //循环创建三个布局
        for(int i=0;i<TILTE.length;i++){
            VideoCommonFragment videoCommonFragment = new VideoCommonFragment();
            fragments.add(videoCommonFragment);
        }
        videoViewPager.setOffscreenPageLimit(3);
    }

    private void initView() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

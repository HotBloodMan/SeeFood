package com.ljt.seefood.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.gson.Gson;
import com.ljt.seefood.R;
import com.ljt.seefood.adapter.FoodAdapter;
import com.ljt.seefood.adapter.VpAdapter;
import com.ljt.seefood.entity.FoodRoot;
import com.ljt.seefood.ui.MainActivity;
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
public class HomeFragment extends Fragment {

    public static final String TAG=HomeFragment.class.getSimpleName();

    @Bind(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
//    @Bind(R.id.viewpager)
    ViewPager vPagerHomeFrag;

    VpAdapter vpAdapter;


    private static final String[] TITLE=new String[]{"头条","社会","娱乐","国际","科技","体育","军事","国内","财经","时尚"};
     List<Fragment> fragments=new ArrayList<>();
     List<String> titleList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view= inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        vPagerHomeFrag = (ViewPager) view.findViewById(R.id.viewpager);
        initTitleData();
        setAdapter();
        initWidget(view);
        return view;
    }

    private void initTitleData() {
        Log.d(TAG,"aaaaaaa----->>>> 1111");
        titleList = new ArrayList<String>();
        titleList.add("头条");
        titleList.add("社会");
        titleList.add("娱乐");
        titleList.add("国际");
        titleList.add("科技");
        titleList.add("体育");
        titleList.add("军事");
        titleList.add("国内");
        titleList.add("财经");
        titleList.add("时尚");
//          for (int i = 0; i < TITLE.length; i++) {
//            CommonHotFragment commonHotFragment = new CommonHotFragment();
//            fragments.add(commonHotFragment);
//        }
        vPagerHomeFrag.setOffscreenPageLimit(3);
                    FragmentH1 fragmentH1 = new FragmentH1();
                    Fragment2 fragmentH2 = new Fragment2();
                    Fragment3 fragmentH3 = new Fragment3();
                    Fragment4 fragmentH4 = new Fragment4();
                    Fragment5 fragmentH5 = new Fragment5();
                    Fragment6 fragmentH6 = new Fragment6();
                    Fragment7 fragmentH7 = new Fragment7();
                    Fragment8 fragmentH8 = new Fragment8();
                    Fragment9 fragmentH9 = new Fragment9();
                    Fragment10 fragmentH10 = new Fragment10();
                    fragments.add(fragmentH1);
                    fragments.add(fragmentH2);
                    fragments.add(fragmentH3);
                    fragments.add(fragmentH4);
                    fragments.add(fragmentH5);
                    fragments.add(fragmentH6);
                    fragments.add(fragmentH7);
                    fragments.add(fragmentH8);
                    fragments.add(fragmentH9);
                    fragments.add(fragmentH10);
    }

    private void setAdapter() {
        Log.d(TAG,"aaaaaaa----->>>> 2222");
       //实例化适配器
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getFragmentManager(),
                FragmentPagerItems.with(getContext())
                        .add(TITLE[0], fragments.get(0).getClass())
                        .add(TITLE[1], fragments.get(1).getClass())
                        .add(TITLE[2], fragments.get(2).getClass())
                        .add(TITLE[3], fragments.get(3).getClass())
                        .add(TITLE[4], fragments.get(4).getClass())
                        .add(TITLE[5], fragments.get(5).getClass())
                        .add(TITLE[6], fragments.get(6).getClass())
                        .add(TITLE[7], fragments.get(7).getClass())
                        .add(TITLE[8], fragments.get(8).getClass())
                        .add(TITLE[9], fragments.get(9).getClass())
                        .create());
//        设置适配器
        vPagerHomeFrag.setAdapter(adapter);
        viewpagertab.setViewPager(vPagerHomeFrag);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private void initWidget(View view) {
        Log.d(TAG,"aaaaaaa----->>>> 33333");

        vpAdapter = new VpAdapter(getActivity().getSupportFragmentManager(), titleList);
        Log.d(TAG,"aaaaaaa----->>>> 4444");
        vPagerHomeFrag.setAdapter(vpAdapter);
    }
}

package com.ljt.seefood.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ljt.seefood.fragment.FragmentH1;
import com.ljt.seefood.fragment.FragmentH2;

import java.util.List;

/**
 * Created by 1 on 2017/3/23.
 */

public class VpAdapter extends FragmentPagerAdapter {
    private List<String> mList;

    public VpAdapter(FragmentManager fm,List<String> list) {
        super(fm);
        this.mList=list;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FragmentH1.getInstance();
            case 1:
                return FragmentH2.getInstance();

        }
        return null;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).toString();
    }
}

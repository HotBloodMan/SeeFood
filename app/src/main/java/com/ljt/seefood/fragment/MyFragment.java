package com.ljt.seefood.fragment;


import android.animation.Animator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.ljt.seefood.R;
import com.ljt.seefood.ui.MainActivity;
import com.ljt.seefood.ui.SettingActivity;
import com.ljt.seefood.utils.ConstanceValue;
import com.ljt.seefood.utils.SharedPreferencesMgr;
import com.ljt.seefood.utils.ThemeSetting;
import com.zt.jackone.AppConnect;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends  Fragment implements View.OnClickListener{

    @Bind(R.id.app_recommend_layout)
    RelativeLayout rlRecommend;
    @Bind(R.id.clear_cache_layout)
    RelativeLayout rlClearCache;
    @Bind(R.id.nightmode_layout)
    RelativeLayout rlThemeMode;



    private View view;
    private Toolbar toolbar;

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this,view);
//        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        initListener();

        return view;
    }

    private void initListener() {
        rlRecommend.setOnClickListener(this);
        rlClearCache.setOnClickListener(this);
        rlThemeMode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_recommend_layout:
                AppConnect.getInstance(getActivity()).showAppOffers(getActivity());
                break;
            case R.id.clear_cache_layout:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("确定要清除所有缓存吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setNegativeButton("取消", null).show();
                break;
            case R.id.nightmode_layout:


        }}

}
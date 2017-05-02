package com.ljt.seefood.ui;

import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.ljt.seefood.R;
import com.ljt.seefood.fragment.MyFragment;
import com.ljt.seefood.utils.ThemeChangedEvent;
import com.ljt.seefood.utils.ThemeSetting;
import com.ljt.seefood.utils.ThemeUtil;

import org.greenrobot.eventbus.EventBus;

public class SettingActivity extends BaseActivity implements
        ColorChooserDialog.ColorCallback{

    protected Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initToolBar();
    }
    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_setting);
        setSupportActionBar(toolbar);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }
    @Override
    protected int getMenuId() {
        return 0;
    }
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void loadData() {
//        getFragmentManager().beginTransaction().replace(R.id.contentLayout, new MyFragment()).commit();
    }
    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {
        if (selectedColor == ThemeUtil.getThemeColor(this, R.attr.colorPrimary))
            return;
        toolbar.setBackgroundColor(selectedColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(selectedColor);
        }
        if (selectedColor == getResources().getColor(R.color.lapis_blue)) {
            setTheme(R.style.LapisBlueTheme);
            ThemeSetting.setTheme(0);
        } else if (selectedColor == getResources().getColor(R.color.pale_dogwood)) {
            setTheme(R.style.PaleDogwoodTheme);
            ThemeSetting.setTheme(1);
        } else if (selectedColor == getResources().getColor(R.color.greenery)) {
            setTheme(R.style.GreeneryTheme);
            ThemeSetting.setTheme(2);
        } else if (selectedColor == getResources().getColor(R.color.primrose_yellow)) {
            setTheme(R.style.PrimroseYellowTheme);
            ThemeSetting.setTheme(3);
        } else if (selectedColor == getResources().getColor(R.color.flame)) {
            setTheme(R.style.FlameTheme);
            ThemeSetting.setTheme(4);
        } else if (selectedColor == getResources().getColor(R.color.island_paradise)) {
            setTheme(R.style.IslandParadiseTheme);
            ThemeSetting.setTheme(5);
        } else if (selectedColor == getResources().getColor(R.color.kale)) {
            setTheme(R.style.KaleTheme);
            ThemeSetting.setTheme(6);
        } else if (selectedColor == getResources().getColor(R.color.pink_yarrow)) {
            setTheme(R.style.PinkYarrowTheme);
            ThemeSetting.setTheme(7);
        } else if (selectedColor == getResources().getColor(R.color.niagara)) {
            setTheme(R.style.NiagaraTheme);
            ThemeSetting.setTheme(8);
        }
//        getFragmentManager().beginTransaction().replace(R.id.contentLayout, new MyFragment()).commit();
        EventBus.getDefault().post(new ThemeChangedEvent(selectedColor));
    }
}

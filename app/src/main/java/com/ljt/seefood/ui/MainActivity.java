package com.ljt.seefood.ui;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.ljt.seefood.R;
import com.ljt.seefood.fragment.HomeFragment;
import com.ljt.seefood.fragment.MyFragment;
import com.ljt.seefood.fragment.SearchFoodFragment;
import com.ljt.seefood.utils.ThemeSetting;
import com.ljt.seefood.utils.ThemeUtil;
import com.zt.jackone.AppConnect;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv_daily_food_main)
    TextView tvHomeFood;
    @Bind(R.id.tv_find_food_main)
    TextView tvFindFood;
    @Bind(R.id.tv_my_food_main)
    TextView tvMyFood;

    public static final String TAG=MainActivity.class.getSimpleName();

//    @Bind(R.id.toolbar)
//    Toolbar toolbar;

    FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private HomeFragment homeFragment;
    private SearchFoodFragment searchFoodFragment;
    private MyFragment myFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件
        ButterKnife.bind(this);
//        setSupportActionBar(toolbar);
        initViews();
    }

    private void initViews() {
        fragmentManager = getSupportFragmentManager();
        //设置默认第一个菜单按钮为选中状态
        setChoice(1);
    }

    //底部菜单栏的点击切换
    private void setChoice(int currentItem){
        Log.d(TAG,"aaa---->>> 111");
        //初始化广告参数
        AppConnect.getInstance(this);

        transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        clearChoice();
        switch (currentItem){
            case 1://食物主页
                tvHomeFood.setTextColor(getResources().getColor(R.color.colorBlack));
                if(homeFragment==null){
                    homeFragment=new HomeFragment();
                    transaction.add(R.id.main_ll_fragment,homeFragment);
                }else{
                    transaction.show(homeFragment);
                }
                break;
//            case 2://食物百科搜索
//                tvFindFood.setTextColor(getResources().getColor(R.color.colorBlack));
//                if(searchFoodFragment==null){
//                    searchFoodFragment=new SearchFoodFragment();
//                    transaction.add(R.id.main_ll_fragment,searchFoodFragment);
//                }else{
//                    transaction.show(searchFoodFragment);
//                }
//                break;
//            case 3://我的食物
//                tvMyFood.setTextColor(getResources().getColor(R.color.colorBlack));
//                if(myFragment==null){
//                    myFragment=new MyFragment();
//                    transaction.add(R.id.main_ll_fragment,myFragment);
//                }else{
//                    transaction.show(myFragment);
//                }
////                startActivity(new Intent(MainActivity.this,SettingActivity.class));
//                break;
        }
        //提交事务
        transaction.commit();
    }
    //隐藏所有的Fragment,避免Fragment混乱
    private void hideFragments(FragmentTransaction transaction){
        Log.d(TAG,"aaa---->>> 222");
        if(homeFragment!=null){
            transaction.hide(homeFragment);
        }
//        if(searchFoodFragment!=null){
//            transaction.hide(searchFoodFragment);
//        }
//        if(myFragment!=null){
//            transaction.hide(myFragment);
//        }

    }
    //重置所有选项
    private void clearChoice(){
        Log.d(TAG,"aaa---->>> 333");
        //还原默认选项
        tvHomeFood.setTextColor(getResources().getColor(R.color.colorGray));
        tvFindFood.setTextColor(getResources().getColor(R.color.colorGray));
        tvMyFood.setTextColor(getResources().getColor(R.color.colorGray));
    }

    @OnClick({R.id.tv_daily_food_main,R.id.tv_find_food_main,R.id.tv_my_food_main})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_daily_food_main:
                setChoice(1);
                break;
            case R.id.tv_find_food_main:
                setChoice(2);
                break;
            case R.id.tv_my_food_main:
                setChoice(3);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppConnect.getInstance(this).close();
    }
}

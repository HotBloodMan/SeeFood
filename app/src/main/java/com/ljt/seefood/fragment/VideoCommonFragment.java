package com.ljt.seefood.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ljt.seefood.R;
import com.ljt.seefood.adapter.CommonAdapter;
import com.ljt.seefood.entity.HotStraetgyEntity;
import com.ljt.seefood.ui.VideoDetailActivity;
import com.ljt.seefood.utils.HttpAddress;
import com.ljt.seefood.utils.ViewHolder;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoCommonFragment extends Fragment {

    public static String TAG=VideoCommonFragment.class.getSimpleName();
    private List<HotStraetgyEntity.ItemListEntity> itemListEntities=new ArrayList<>();
    public VideoCommonFragment() {
        // Required empty public constructor
    }
    @Bind(R.id.hot_listview)
    ListView videoListView;
    private static final String[] STRATEGY=new String[]{"weekly","monthly","historical"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_common, container, false);
        //获取控件
        ButterKnife.bind(this,view);

        initView();
        initData();
        setListener();
        return view;
    }
    //初始化控件
    private void initView() {
        //添加底部布局
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_footer, null);
        videoListView.addFooterView(view,null,false);
    }
    //初始化数据
    private void initData(){

        //获取排行
        int position = FragmentPagerItem.getPosition(getArguments());
        Log.d(TAG,"aaa---->>> position VideoCFrag--->>initData"+position);
        String stretary = STRATEGY[position];
        //获取排行请求地址
        String url = String.format(HttpAddress.HOT_STRATEGY, stretary);
        Log.d(TAG,"aaa------>>> url "+url);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pareJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
        requestQueue.start();
    }

    //解析json数据
    private void pareJson(String jsonData){
        HotStraetgyEntity hotStraetgyEntity = new Gson().fromJson(jsonData, HotStraetgyEntity.class);
        itemListEntities.addAll(hotStraetgyEntity.getItemList());
        //设置适配器
        setAdapter(itemListEntities);
    }
    //设置适配器
    private void setAdapter(final List<HotStraetgyEntity.ItemListEntity> dataEntity){
        final int[] i={0};//设置数据的序号
        videoListView.setAdapter(new CommonAdapter<HotStraetgyEntity.ItemListEntity>(getContext(),
                dataEntity,R.layout.list_video_item) {
            @Override
            public void convert(ViewHolder viewHolder, HotStraetgyEntity.ItemListEntity itemListEntity) {
              viewHolder.setText(R.id.tv_title,itemListEntity.getData().getTitle());
                //获取时间
                int duration = itemListEntity.getData().getDuration();
                Log.d(TAG,"aaa----->>> duration== "+duration);
                int mm=duration/60; //分
                int ss = duration % 60;//秒
                String second="";//秒
                String minute="";//分
                if(ss<10){
                    second="0"+String.valueOf(ss);
                }else{
                    second=String.valueOf(ss);
                }
                if(mm<10){
                    minute="0"+String.valueOf(mm);
                }else{
                    minute=String.valueOf(mm);//分钟
                }
                viewHolder.setText(R.id.tv_time,"#"+itemListEntity.getData().getCategory()+"/"+minute+"'"+second+'"');
                viewHolder.setImageResourcewithFresco(R.id.iv, Uri.parse(itemListEntity.getData().getCover().getFeed()));

                if(i[0]<dataEntity.size()){
                    viewHolder.setText(R.id.hot_tv_textnumber,++i[0]+".");
                }
            }
        });
    }
    //设置事件监听
    private void setListener(){
        //listview点击事件
        videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"aaa----->>>>setListener() ");
                Intent intent = new Intent(getContext(), VideoDetailActivity.class);
                Bundle bundle=new Bundle();
                Log.i("--->",position+"");
                HotStraetgyEntity.ItemListEntity.DataEntity data = itemListEntities.get(position).getData();
                bundle.putString("title", data.getTitle());
                //获取到时间
                int duration = data.getDuration();
                int mm = duration / 60;//分
                int ss = duration % 60;//秒
                String second = "";//秒
                String minute = "";//分
                if (ss < 10) {
                    second = "0" + String.valueOf(ss);
                } else {
                    second = String.valueOf(ss);
                }
                if (mm < 10) {
                    minute = "0" + String.valueOf(mm);
                } else {
                    minute = String.valueOf(mm);//分钟
                }
                bundle.putString("time", "#" + data.getCategory()+ " / " + minute + "'" + second + '"');
                bundle.putString("desc",data.getDescription());//视频描述
                bundle.putString("blurred",data.getCover().getBlurred());//模糊图片地址
                bundle.putString("feed",data.getCover().getFeed());//图片地址
                bundle.putString("video",data.getPlayUrl());//视频播放地址
                bundle.putInt("collect",data.getConsumption().getCollectionCount());//收藏量
                bundle.putInt("share",data.getConsumption().getShareCount());//分享量
                bundle.putInt("reply",data.getConsumption().getReplyCount());//回复数量
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

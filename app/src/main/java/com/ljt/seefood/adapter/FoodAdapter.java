package com.ljt.seefood.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljt.seefood.R;
import com.ljt.seefood.entity.FoodRoot;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 1 on 2017/3/8.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    public static final String TAG=FoodAdapter.class.getSimpleName();
    private Context mContext;
    private LayoutInflater mInflater;
    private List<FoodRoot.Result.Data> mList;

    public FoodAdapter(Context context, List<FoodRoot.Result.Data> list){
        this.mContext=context;
        mInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mList=list;
    }

    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_food_layout, parent, false);
        ViewHolder holder=new ViewHolder(view);
        holder.ivFoodImg= (ImageView) view.findViewById(R.id.iv_food_img);
        holder.tvFoodName= (TextView) view.findViewById(R.id.tv_food_name);
        holder.tvFoodTags= (TextView) view.findViewById(R.id.tv_food_tags);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List<String> stringImg = mList.get(position).getString();
        String imgUri = stringImg.get(0);
        ImageLoader.getInstance().displayImage(imgUri,holder.ivFoodImg);
        String titleFood = mList.get(position).getTitle();
        String tagsFood = mList.get(position).getTags();
        holder.tvFoodName.setText("食物:"+getFileIndex(position)+titleFood);
        holder.tvFoodTags.setText("标签:"+tagsFood);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivFoodImg;
        TextView tvFoodName,tvFoodTags;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    //为每一个食物添加数字
    private String getFileIndex(int position) {
        position++;
        String index;
        if (position < 10) {
            index = "0" + position + "  ";
        } else {
            index = position + "   ";
        }
        return index;
    }
}

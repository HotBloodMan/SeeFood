package com.ljt.seefood.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ljt.seefood.utils.ViewHolder;

import java.util.List;

/**
 * Created by 1 on 2017/3/24.
 */

public abstract class CommonAdapter<T> extends BaseAdapter{
   //protected 让子类可以访问
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    private  int layoutId;
    public CommonAdapter(Context context,List<T> mDatas,int layoutId){
        mInflater=LayoutInflater.from(context);
        this.mContext=context;
        this.mDatas=mDatas;
        this.layoutId=layoutId;
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //实例化一个ViewHolder
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent, layoutId, position);
        //把viewHolder和当前Item对于的Bean对象传出去
        convert(viewHolder,getItem(position));
        return viewHolder.getConvertView();
    }
    public abstract void convert(ViewHolder viewHolder,T t);

}

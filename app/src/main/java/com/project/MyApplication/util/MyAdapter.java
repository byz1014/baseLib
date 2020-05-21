package com.project.MyApplication.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private static final int ITEM_COUNT = 300;
    private WeakReference weakContext = null;
    //将所有的view用弱引用缓存起来
    private List<WeakReference<View>> views = null;
    private Context context;

    public MyAdapter(Context context) {
        this.context = context;
        views = new ArrayList<>(ITEM_COUNT);
        for (int i = 0; i < ITEM_COUNT; i++) {
            views.add(null);
        }
    }


    @Override
    public int getCount() {
        return 300;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //初始化基本的占位布局
        TextView textView = null;
        if (convertView == null) {
            textView = new TextView((Context) weakContext.get());
            textView.setGravity(Gravity.BOTTOM | Gravity.CENTER);
            textView.setWidth(400);
            textView.setHeight(200);
            convertView = textView;
        } else {
            textView = (TextView) convertView;
            textView.setBackgroundResource(0);
        }
        textView.setTag(position);
        textView.setText(position + "：正在准备加载");

        views.set(position, new WeakReference<View>(textView));
        return textView;
    }

    public List<WeakReference<View>> getBaseView() {
        return views;
    }
}

package com.project.MyApplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {
    String[] message;
    Context mContext;

    public MainAdapter(String[] message, Context mContext) {
        this.message = message;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return message.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.tv_title = view.findViewById(R.id.tv_title);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
            viewHolder.tv_title.setText(message[i]);
        return view;
    }


 private class ViewHolder{

        TextView tv_title;

 }

}

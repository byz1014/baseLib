package com.project.MyApplication.util;

import android.os.AsyncTask;
import android.widget.TextView;

import com.project.MyApplication.R;

import java.lang.ref.WeakReference;

public class MyAsyTask extends AsyncTask<Integer, String, String> {
    private static final String TAG = "BackGroundRequest";
    private int position;
    private WeakReference textViewWeakReference = null;

    //重写构造方法，因为需要传入参数
    public MyAsyTask(TextView textView) {
        textViewWeakReference = new WeakReference(textView);
    }

    //开始前，应该做的准备
    @Override
    protected String doInBackground(Integer... params) {
        //初始化下标参数
        position = params[0];
        //设置正在加载时显示的progress
        try {
            publishProgress(TAG + "正在加载中");
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return position + "加载完成！！！";
    }

    //正在加载的情况
    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        if (textViewWeakReference == null) {
            ((TextView) textViewWeakReference.get()).setText(values[0]);
        }
    }

    protected void onPostExecute(String s) {
        if (textViewWeakReference != null) {
            ((TextView) textViewWeakReference.get()).setBackgroundResource(R.mipmap.ic_launcher);
            ((TextView) textViewWeakReference.get()).setText(s);
        }
    }
}

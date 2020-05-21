package com.example.baselib;


import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BaseHttpUtil {
    private static BaseHttpUtil mBaseHttpUtil;

    private BaseHttpUtil() {
    }

    public static BaseHttpUtil getBaseHttpUtil() {
        if (mBaseHttpUtil == null) {
            synchronized (BaseHttpUtil.class) {
                if (mBaseHttpUtil == null) {
                    mBaseHttpUtil = new BaseHttpUtil();
                }
            }
        }
        return mBaseHttpUtil;
    }

    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .callTimeout(6_000, TimeUnit.MILLISECONDS)
            .connectTimeout(6_000, TimeUnit.MILLISECONDS)
            .readTimeout(20_000, TimeUnit.MILLISECONDS)
            .writeTimeout(20_000, TimeUnit.MILLISECONDS)
            .build();

    private static String mUrl = null;

    public void setUrl(String url) {
        mUrl = url;
    }


    public void setHeader() {
//        Request request = new Request.Builder()
//                .header("Accept","image/webp")
//                .addHeader("Charset","UTF-8")
//                .url(url)
//                .build();

    }

    public void get(Map<String, String> mMap, String method) {
        Set<String> set = mMap.keySet();
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : set) {
            stringBuilder.append(key)
                    .append("=")
                    .append(mMap.get(key))
                    .append("&");
        }
        String urlLose = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
        Request request = new Request.Builder()
                .url(mUrl + "?" +urlLose)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new BaseOkHttpCallBack(method, new BaseOkHttpCallBack.CallBackListener() {
            @Override
            public void onFailure(Call call, String method, IOException e) {
                String message = e == null ? "erroe == null" : e.getMessage();
                HttpSerializable httpSerializable = new HttpSerializable(message, -1, method);
                EventBus.getDefault().post(httpSerializable);
            }

            @Override
            public void onResponse(Call call, String method, Response response) throws IOException {
                String json = response.body().string();
                HttpSerializable httpSerializable = new HttpSerializable(json, 200, method);
                EventBus.getDefault().post(httpSerializable);
            }
        }));
    }


    public void post(Map<String, String> mMap, String method) {
        FormBody.Builder builder = new FormBody.Builder();
        Set<String> set = mMap.keySet();
        for (String key : set) {
            builder.add(key, mMap.get(key));
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(mUrl)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new BaseOkHttpCallBack(method, new BaseOkHttpCallBack.CallBackListener() {
            @Override
            public void onFailure(Call call, String method, IOException e) {
                String message = e == null ? "erroe == null" : e.getMessage();
                HttpSerializable httpSerializable = new HttpSerializable(message, -1, method);
                EventBus.getDefault().post(httpSerializable);
            }

            @Override
            public void onResponse(Call call, String method, Response response) throws IOException {
                String json = response.body().string();
                HttpSerializable httpSerializable = new HttpSerializable(json, 200, method);
                EventBus.getDefault().post(httpSerializable);
            }
        }));
    }

}

package com.example.baselib;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BaseOkHttpCallBack implements Callback {
    private CallBackListener mCallBackListener;
    private String method;

    public BaseOkHttpCallBack(String method,CallBackListener mCallBackListener) {
        this.mCallBackListener = mCallBackListener;
        this.method = method;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        if(mCallBackListener!=null){
            mCallBackListener.onFailure(call,method,e);
        }
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if(mCallBackListener!=null){
            mCallBackListener.onResponse(call,method,response);
        }
    }


    public interface CallBackListener {
          void onFailure(Call call, String method, IOException e);

          void onResponse(Call call, String method, Response response) throws IOException;
    }


}

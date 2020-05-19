package com.project.MyApplication;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;


public class WindownUtil {
    private static WindownUtil mWindownUtil;
    private WindownUtil(){}
    public static WindownUtil getWindownUtil(){
        if(mWindownUtil == null){
            synchronized (WindownUtil.class){
                if(mWindownUtil == null){
                    mWindownUtil = new WindownUtil();
                }
            }
        }
        return mWindownUtil;
    }

    WindowManager wm;
    Display display;
    public int getWidth(){
        if(display == null){
            wm = (WindowManager)MyApplication.getMyApplication().getSystemService(Context.WINDOW_SERVICE);
            display = wm.getDefaultDisplay();
        }
      return display.getWidth();
    }

    public int getHeight(){
        if(display == null){
            wm = (WindowManager)MyApplication.getMyApplication().getSystemService(Context.WINDOW_SERVICE);
            display = wm.getDefaultDisplay();
        }
        return display.getHeight();
    }



}

package com.example.baselib;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public abstract class BaseApplication extends Application {
    private static BaseApplication mBaseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                onNowCallBackActivity(activity,0,bundle);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                onNowCallBackActivity(activity,1,null);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                onNowCallBackActivity(activity,2,null);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                onNowCallBackActivity(activity,3,null);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                onNowCallBackActivity(activity,4,null);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                onNowCallBackActivity(activity,5,bundle);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                onNowCallBackActivity(activity,6,null);
            }

        });

    }

    public static BaseApplication getBaseApplication() {
        return mBaseApplication;
    }


    protected abstract void onNowCallBackActivity(Activity mActivity,int lifeStatus, Bundle bundle);
}

package com.project.MyApplication;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class MyApplication extends Application {
    private static MyApplication myApplication;
    private Activity mActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                mActivity = activity;
            }
            @Override
            public void onActivityStarted(Activity activity) {
                mActivity = activity;
            }

            @Override
            public void onActivityResumed(Activity activity) {
                mActivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {
                mActivity = activity;
            }

            @Override
            public void onActivityStopped(Activity activity) {
                mActivity = activity;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                mActivity = activity;
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                mActivity = activity;
            }
        });

    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    public Activity getActivity() {
        return mActivity;
    }


}

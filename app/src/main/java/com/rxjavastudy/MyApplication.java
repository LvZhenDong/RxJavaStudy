package com.rxjavastudy;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/7/3.
 */

public class MyApplication extends Application {

    public static final String LOG_TAG = "kklv";

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}

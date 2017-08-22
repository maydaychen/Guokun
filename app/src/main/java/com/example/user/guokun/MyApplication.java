package com.example.user.guokun;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by user on 2017/8/18.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        SDKInitializer.initialize(getApplicationContext());
        super.onCreate();
    }
}

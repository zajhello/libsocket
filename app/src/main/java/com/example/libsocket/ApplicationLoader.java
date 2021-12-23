package com.example.libsocket;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;

import androidx.multidex.MultiDex;

public class ApplicationLoader extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Used to load the 'libsocket' library on application startup.
        System.loadLibrary("libsocket");
    }
}

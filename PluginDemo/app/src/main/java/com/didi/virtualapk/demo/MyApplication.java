package com.didi.virtualapk.demo;

import com.didi.virtualapk.demo.utils.MyUtils;

import android.app.Application;
import android.os.Process;
import android.util.Log;
import com.didi.virtualapk.PluginManager;
import com.didi.virtualapk.internal.*;
import java.util.List;

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = MyUtils.getProcessName(getApplicationContext(),
                Process.myPid());
        Log.d(TAG, "application start, process name:" + processName);
        new Thread(new Runnable() {

            @Override
            public void run() {
                doWorkInBackground();
            }
        }).start();
    }

    private void doWorkInBackground() {
        // init binder pool
        //BinderPool.getInsance(getApplicationContext());
        isPlugin();
    }

    private boolean isPlugin() {
        List<LoadedPlugin> pluginList = PluginManager.getInstance(this).getAllLoadedPlugins();
        Log.d(TAG, "===================printLoadedPluginsBegin:pluginManager:" + PluginManager.getInstance(this).toString());
        for (LoadedPlugin plugin: pluginList){
            Log.d(TAG, "===========pluginInfo:" + plugin.getPackageName());
        }
        Log.d(TAG, "===================printLoadedPluginsEnd:");
        return null != PluginManager.getInstance(this).getLoadedPlugin("com.didi.virtualapk.demo");
    }
}

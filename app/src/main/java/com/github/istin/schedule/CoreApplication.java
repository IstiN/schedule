package com.github.istin.schedule;

import android.app.Application;
import android.content.Context;

import com.github.istin.schedule.manager.ConfigurationManager;
import com.github.istin.schedule.manager.ThreadManager;

/**
 * Created by uladzimir_klyshevich on 10/5/15.
 */
public class CoreApplication extends Application {

    private ConfigurationManager mConfigurationManager;

    private ThreadManager mThreadManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mConfigurationManager = new ConfigurationManager(this);
        mThreadManager = new ThreadManager();
    }

    @Override
    public Object getSystemService(String name) {
        if (ConfigurationManager.APP_SERVICE_KEY.equals(name)) {
            return mConfigurationManager;
        }
        if (ThreadManager.APP_SERVICE_KEY.equals(name)) {
            return mThreadManager;
        }
        return super.getSystemService(name);
    }

    public static <T> T get(Context context, String name) {
        if (context == null || name == null) {
            throw new IllegalArgumentException("Context and key must not be null");
        }
        T systemService = (T) context.getSystemService(name);
        if (systemService == null) {
            context = context.getApplicationContext();
            systemService = (T) context.getSystemService(name);
        }
        if (systemService == null) {
            throw new IllegalStateException(name + " not available");
        }
        return systemService;
    }
}

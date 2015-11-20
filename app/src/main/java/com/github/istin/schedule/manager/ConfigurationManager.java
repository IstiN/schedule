package com.github.istin.schedule.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.github.istin.schedule.CoreApplication;
import com.github.istin.schedule.gson.Lecturer;
import com.github.istin.schedule.gson.University;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by uladzimir_klyshevich on 11/20/15.
 */
public class ConfigurationManager {

    public static final String KEY_CONFIG = "_config";

    public static final String APP_SERVICE_KEY = "config:manager";

    public static ConfigurationManager get(Context pContext) {
        return CoreApplication.get(pContext, APP_SERVICE_KEY);
    }

    public boolean isConfigured() {
        return getConfig() != null;
    }

    public static class Config implements Serializable {

        @SerializedName("l")
        private Lecturer mLecturer;

        @SerializedName("u")
        private University mUniversity;

        public Config(University pUniversity, Lecturer pLecturer) {
            mLecturer = pLecturer;
            mUniversity = pUniversity;
        }

        public Lecturer getLecturer() {
            return mLecturer;
        }

        public University getUniversity() {
            return mUniversity;
        }

    }

    private Config mConfig;

    private Context mContext;

    private Object mLock = new Object();

    private boolean isInited = false;

    public ConfigurationManager(Context pContext) {
        mContext = pContext;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isInited) {
                    return;
                }
                firstInitialization();
            }
        }).start();
    }

    protected void firstInitialization() {
        synchronized (mLock) {
            if (isInited) {
                return;
            }
            final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            final String value = defaultSharedPreferences.getString(KEY_CONFIG, null);
            if (value != null) {
                mConfig = new Gson().fromJson(value, Config.class);
            }
            isInited = true;
        }
    }

    public void setConfig(Config pConfig) {
        mConfig = pConfig;
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        final String value = new Gson().toJson(mConfig, Config.class);
        defaultSharedPreferences.edit().putString(KEY_CONFIG, value).apply();
    }

    public Config getConfig() {
        if (isInited) {
            return mConfig;
        }
        firstInitialization();
        return mConfig;
    }
}

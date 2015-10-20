package com.github.istin.schedule.http;

import com.github.istin.schedule.Api;
import com.github.istin.schedule.manager.ThreadManager;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;

/**
 * Created by uladzimir_klyshevich on 10/20/15.
 */
public class HttpJob<Result> implements ThreadManager.IJob<Result> {

    private String mUrl;

    private Class<Result> mResultClass;

    public HttpJob(String pUrl, Class<Result> pResultClass) {
        mUrl = pUrl;
        mResultClass = pResultClass;
    }

    @Override
    public Result doJob() throws Exception {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = HttpUtils.getInputStream(mUrl);
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader, 8192);
            //return new Gson().fromJson(bufferedReader, returnedClass());
            return new Gson().fromJson(bufferedReader, mResultClass);
        } finally {
            HttpUtils.close(inputStream);
            HttpUtils.close(inputStreamReader);
            HttpUtils.close(bufferedReader);
        }
    }

    public Class<Result> returnedClass() {
        ParameterizedType parameterizedType = (ParameterizedType)getClass().getGenericSuperclass();
        return (Class<Result>) parameterizedType.getActualTypeArguments()[0];
    }
}

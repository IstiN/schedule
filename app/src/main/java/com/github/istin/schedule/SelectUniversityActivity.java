package com.github.istin.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.istin.schedule.gson.UniversityModel;
import com.github.istin.schedule.utils.HttpUtils;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by uladzimir_klyshevich on 10/6/15.
 */
public class SelectUniversityActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = SelectUniversityActivity.class.getSimpleName();

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        mListView = (ListView) findViewById(android.R.id.list);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = null;
                    InputStreamReader inputStreamReader = null;
                    BufferedReader bufferedReader = null;
                    try {
                        inputStream = HttpUtils.getInputStream(Api.UNIVERSITY_LIST);
                        inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                        bufferedReader = new BufferedReader(inputStreamReader, 8192);
                        final UniversityModel[] universityModels = new Gson().fromJson(bufferedReader, UniversityModel[].class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mListView.setAdapter(
                                        new ArrayAdapter<>(SelectUniversityActivity.this, android.R.layout.simple_list_item_1, universityModels)
                                );
                            }
                        });
                    } finally {
                        HttpUtils.close(inputStream);
                        HttpUtils.close(inputStreamReader);
                        HttpUtils.close(bufferedReader);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void onNextButtonClick(View pView) {
        startActivity(new Intent(this, SelectLecturerActivity.class));
    }

}

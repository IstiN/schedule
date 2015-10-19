package com.github.istin.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.istin.schedule.gson.LecturerModel;
import com.github.istin.schedule.utils.ConfigUtils;
import com.github.istin.schedule.utils.HttpUtils;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by uladzimir_klyshevich on 10/6/15.
 */
public class SelectLecturerActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = SelectLecturerActivity.class.getSimpleName();

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
                        inputStream = HttpUtils.getInputStream(Api.LECTURER_LIST);
                        inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                        bufferedReader = new BufferedReader(inputStreamReader, 8192);
                        final LecturerModel[] lecturerModels = new Gson().fromJson(bufferedReader, LecturerModel[].class);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mListView.setAdapter(
                                        new ArrayAdapter<>(SelectLecturerActivity.this, android.R.layout.simple_list_item_1, lecturerModels)
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
        startActivity(new Intent(this, StartActivity.class));
        ConfigUtils.saveConfig();
    }

}

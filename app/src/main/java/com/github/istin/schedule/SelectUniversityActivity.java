package com.github.istin.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.istin.schedule.gson.UniversityModel;
import com.github.istin.schedule.manager.ThreadManager;
import com.github.istin.schedule.utils.HttpUtils;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by uladzimir_klyshevich on 10/6/15.
 */
public class SelectUniversityActivity extends AppCompatActivity implements ThreadManager.IExecutionListener<UniversityModel[]> {

    private static final String DEBUG_TAG = SelectUniversityActivity.class.getSimpleName();

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        mListView = (ListView) findViewById(android.R.id.list);
        //TODO HW new Job<UniversityModel[]>(Api.UNIVERSITY_LIST)
        new ThreadManager().execute(new ThreadManager.IJob<UniversityModel[]>() {
            @Override
            public UniversityModel[] doJob() throws Exception {
                InputStream inputStream = null;
                InputStreamReader inputStreamReader = null;
                BufferedReader bufferedReader = null;
                try {
                    inputStream = HttpUtils.getInputStream(Api.UNIVERSITY_LIST);
                    inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    bufferedReader = new BufferedReader(inputStreamReader, 8192);
                    final UniversityModel[] universityModels = new Gson().fromJson(bufferedReader, UniversityModel[].class);
                    return universityModels;
                } finally {
                    HttpUtils.close(inputStream);
                    HttpUtils.close(inputStreamReader);
                    HttpUtils.close(bufferedReader);
                }
            }

        }, this);
    }

    @Override
    public void onJobStarted() {
        //TODO show progress
    }

    @Override
    public void onDone(final UniversityModel[] pUniversityModels) {
        //TODO hide progress
        mListView.setAdapter(
                new ArrayAdapter<>(SelectUniversityActivity.this, android.R.layout.simple_list_item_1, pUniversityModels)
        );
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(SelectUniversityActivity.this, SelectLecturerActivity.class);
                final UniversityModel universityModel = pUniversityModels[position];
                intent.putExtra(SelectLecturerActivity.EXTRA_ID, universityModel.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onError(Exception e) {
        //TODO hide progress
        //TODO show error dialog
    }
}

package com.github.istin.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.istin.schedule.gson.UniversityModel;
import com.github.istin.schedule.http.HttpJob;
import com.github.istin.schedule.manager.ThreadManager;
import com.github.istin.schedule.http.HttpUtils;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by uladzimir_klyshevich on 10/6/15.
 */
public class SelectUniversityActivity extends CommonHttpJobActivity<UniversityModel[]> {

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_select;
    }

    @Override
    protected Class<UniversityModel[]> getResultClass() {
        return UniversityModel[].class;
    }

    @Override
    protected String getUrl() {
        return Api.UNIVERSITY_LIST;
    }

    @Override
    protected void applyResult(final UniversityModel[] pUniversityModels) {
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(
                new ArrayAdapter<>(SelectUniversityActivity.this, android.R.layout.simple_list_item_1, pUniversityModels)
        );
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(SelectUniversityActivity.this, SelectLecturerActivity.class);
                final UniversityModel universityModel = pUniversityModels[position];
                intent.putExtra(SelectLecturerActivity.EXTRA_ID, universityModel.getId());
                startActivity(intent);
            }
        });
    }

}

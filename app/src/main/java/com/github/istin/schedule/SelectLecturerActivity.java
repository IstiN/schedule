package com.github.istin.schedule;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.istin.schedule.gson.Lecturer;

import java.util.Random;

/**
 * Created by uladzimir_klyshevich on 10/6/15.
 */
public class SelectLecturerActivity extends CommonHttpJobActivity<Lecturer[]> {

    public static final String EXTRA_ID = "_id";

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_select;
    }

    @Override
    protected Class<Lecturer[]> getResultClass() {
        return Lecturer[].class;
    }

    @Override
    protected String getUrl() {
        final String id = getIntent().getStringExtra(EXTRA_ID);
        return Api.LECTURER_LIST + id;
    }

    @Override
    protected void applyResult(Lecturer[] pLecturers) {
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(
                new ArrayAdapter<>(SelectLecturerActivity.this, android.R.layout.simple_list_item_1, pLecturers)
        );
    }

}

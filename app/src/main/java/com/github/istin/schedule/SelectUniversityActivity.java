package com.github.istin.schedule;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.istin.schedule.gson.University;

/**
 * Created by uladzimir_klyshevich on 10/6/15.
 */
public class SelectUniversityActivity extends CommonHttpJobActivity<University[]> {

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_select;
    }

    @Override
    protected Class<University[]> getResultClass() {
        return University[].class;
    }

    @Override
    protected String getUrl() {
        return Api.UNIVERSITY_LIST;
    }

    @Override
    protected void applyResult(final University[] pUniversities) {
        ListView listView = (ListView) findViewById(android.R.id.list);
        final ArrayAdapter<University> adapter = new ArrayAdapter<>(SelectUniversityActivity.this, android.R.layout.simple_list_item_1, pUniversities);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(SelectUniversityActivity.this, SelectLecturerActivity.class);
                final University university = (University) parent.getAdapter().getItem(position);
                intent.putExtra(SelectLecturerActivity.EXTRA_ITEM, university);
                startActivity(intent);
            }
        });
        initFilter(adapter, R.string.search_university_hint);
    }

}

package com.github.istin.schedule;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.github.istin.schedule.gson.Lecturer;
import com.github.istin.schedule.gson.University;
import com.github.istin.schedule.manager.ConfigurationManager;

/**
 * Created by uladzimir_klyshevich on 10/6/15.
 */
public class SelectLecturerActivity extends CommonHttpJobActivity<Lecturer[]> {

    public static final String EXTRA_ITEM = "item";

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
        final University university = (University) getIntent().getSerializableExtra(EXTRA_ITEM);
        return Api.LECTURER_LIST + university.getId();
    }

    @Override
    protected void applyResult(Lecturer[] pLecturers) {
        ListView listView = (ListView) findViewById(android.R.id.list);
        final ArrayAdapter<Lecturer> adapter = new ArrayAdapter<>(SelectLecturerActivity.this, android.R.layout.simple_list_item_1, pLecturers);
        listView.setAdapter(adapter);
        initFilter(adapter, R.string.search_lecturer_hint);
        if (BuildConfig.DEBUG) {
            ((EditText) findViewById(R.id.search_edit_text)).setText("клышевич");
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Lecturer lecturer = (Lecturer) parent.getAdapter().getItem(position);
                ConfigurationManager.Config config = new ConfigurationManager.Config((University) getIntent().getSerializableExtra(EXTRA_ITEM), lecturer);
                ConfigurationManager.get(view.getContext()).setConfig(config);
                final Intent intent = new Intent(SelectLecturerActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });
    }

}

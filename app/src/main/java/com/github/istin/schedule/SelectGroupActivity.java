package com.github.istin.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.istin.schedule.utils.ConfigUtils;

/**
 * Created by uladzimir_klyshevich on 10/6/15.
 */
public class SelectGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
    }

    public void onNextButtonClick(View pView) {
        startActivity(new Intent(this, StartActivity.class));
        ConfigUtils.saveConfig();
    }
}

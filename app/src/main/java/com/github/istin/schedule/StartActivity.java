package com.github.istin.schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.github.istin.schedule.manager.ConfigurationManager;

/**
 * Created by uladzimir_klyshevich on 10/6/15.
 */
public class StartActivity extends Activity {

    private static final String CONFIG_STATE = "config_state";

    private boolean isConfigurationProcessStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            isConfigurationProcessStarted = savedInstanceState.getBoolean(CONFIG_STATE);
            if (isConfigurationProcessStarted) {
                return;
            }
        }
        checkAndRedirect();
    }

    private void checkAndRedirect() {
        if (ConfigurationManager.get(this).isConfigured()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, SelectUniversityActivity.class));
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        checkAndRedirect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isConfigurationProcessStarted) {
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isConfigurationProcessStarted = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(CONFIG_STATE, isConfigurationProcessStarted);
    }
}

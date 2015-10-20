package com.github.istin.schedule;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.github.istin.schedule.http.HttpJob;
import com.github.istin.schedule.manager.ThreadManager;

/**
 * Created by uladzimir_klyshevich on 10/6/15.
 */
public abstract class CommonHttpJobActivity<Result> extends AppCompatActivity implements ThreadManager.IExecutionListener<Result> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayout());
        execute();
    }

    private void execute() {
        new ThreadManager().execute(new HttpJob<>(getUrl(), getResultClass()), this);
    }

    @LayoutRes
    protected abstract int getContentViewLayout();

    protected abstract Class<Result> getResultClass();

    protected abstract String getUrl();

    protected abstract void applyResult(Result pResult);

    protected void setProgressVisibility(int pVisible) {
        final View progressView = findViewById(android.R.id.progress);
        if (progressView != null) {
            progressView.setVisibility(pVisible);
        }
    }

    @Override
    public void onJobStarted() {
        setProgressVisibility(View.VISIBLE);
    }

    @Override
    public void onDone(Result pResult) {
        setProgressVisibility(View.INVISIBLE);
        applyResult(pResult);
    }

    @Override
    public void onError(Exception e) {
        setProgressVisibility(View.INVISIBLE);
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(e.toString())
                .setPositiveButton("Repeat", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        execute();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create().show();
    }
}

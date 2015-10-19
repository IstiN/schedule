package com.github.istin.schedule.manager;

import android.os.Handler;

/**
 * Created by uladzimir_klyshevich on 10/19/15.
 */
public class ThreadManager {

    public interface IExecutionListener<Result> {

        void onJobStarted();

        void onDone(Result pResult);

        void onError(Exception e);

    }

    public interface IJob<Result> {

        Result doJob() throws Exception;

    }

    public <Result> void execute(final IJob<Result> pJob, final IExecutionListener<Result> pExecutionListener) {
        pExecutionListener.onJobStarted();
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Result result = pJob.doJob();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pExecutionListener.onDone(result);
                        }
                    });
                } catch (final Exception e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pExecutionListener.onError(e);
                        }
                    });
                }
            }
        }).start();
    }

}

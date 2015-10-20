package com.github.istin.schedule.http;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by uladzimir_klyshevich on 10/13/15.
 */
public class HttpUtils {

    public static final int READ_TIMEOUT_MILLIS = 10000;
    public static final int CONNECTION_TIMEOUT_MILLIS = 15000;

    public static InputStream getInputStream(String myUrl) throws IOException {
        InputStream is = null;
        URL url = new URL(myUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(READ_TIMEOUT_MILLIS /* milliseconds */);
        conn.setConnectTimeout(CONNECTION_TIMEOUT_MILLIS /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        return conn.getInputStream();
    }

    public static void close(Closeable pCloseable) {
        try {
            if (pCloseable != null) {
                pCloseable.close();
            }
        } catch (IOException e) {
            //can be ignored
        }
    }
}

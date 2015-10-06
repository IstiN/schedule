package com.github.istin.schedule.utils;

/**
 * Created by uladzimir_klyshevich on 10/6/15.
 */
public class ConfigUtils {

    private static boolean sIsConfigured = false;

    public static boolean isConfigured() {
        return sIsConfigured;
    }

    public static void saveConfig() {
        sIsConfigured = true;
    }

}

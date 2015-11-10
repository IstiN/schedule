package com.github.istin.schedule.backend.adapter.grsu.domain;

import com.google.gson.annotations.SerializedName;

public class LessonModel {

    private String id;

    private String date;

    private String timeStart;

    private String timeEnd;

    private String title;

    private String address;

    private String room;

    public void setDate(String pDate) {
        date = pDate;
    }

    public static class Optimized {
        @SerializedName("i")
        private String id;

        @SerializedName("s")
        private String timeStart;

        @SerializedName("e")
        private String timeEnd;

        @SerializedName("d")
        private String date;

        @SerializedName("t")
        private String title;

        @SerializedName("a")
        private String address;

        @SerializedName("r")
        private String room;
    }

    public Optimized doOptimize() {
        final Optimized optimized = new Optimized();
        optimized.id = this.id;
        optimized.timeEnd = this.timeEnd;
        optimized.timeStart = this.timeStart;
        optimized.title = this.title;
        optimized.address = this.address;
        optimized.date = this.date;
        optimized.room = this.room;
        return optimized;
    }
}


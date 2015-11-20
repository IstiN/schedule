package com.github.istin.schedule.backend.adapter.grsu.domain;

import com.github.istin.schedule.gson.Lesson;

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

    public Lesson doOptimize() {
        final Lesson optimized = new Lesson();
        optimized.setId(this.id);
        optimized.setTimeEnd(this.timeEnd);
        optimized.setTimeStart(this.timeStart);
        optimized.setTitle(this.title);
        optimized.setAddress(this.address);
        optimized.setDate(this.date);
        optimized.setRoom(this.room);
        return optimized;
    }
}


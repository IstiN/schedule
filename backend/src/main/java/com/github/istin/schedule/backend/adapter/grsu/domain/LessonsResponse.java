package com.github.istin.schedule.backend.adapter.grsu.domain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LessonsResponse {

    public static class Day {

        private String date;

        @SerializedName("lessons")
        private List<LessonModel> lessons;

    }

    @SerializedName("days")
    private List<Day> days;

    public List<LessonModel> getLessons() {
        final ArrayList<LessonModel> lessonModels = new ArrayList<>();
        if (days != null) {
            for (Day day : days) {
                final List<LessonModel> lessons = day.lessons;
                if (lessons != null) {
                    for (LessonModel lessonModel : lessons) {
                        lessonModel.setDate(day.date);
                        lessonModels.add(lessonModel);
                    }
                }
            }
        }
        return lessonModels;
    }
}


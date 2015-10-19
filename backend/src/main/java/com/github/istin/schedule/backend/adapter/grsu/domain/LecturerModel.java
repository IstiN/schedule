package com.github.istin.schedule.backend.adapter.grsu.domain;

import com.github.istin.schedule.gson.Lecturer;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yury on 15.10.2015.
 */
public class LecturerModel {

    private String id;

    @SerializedName("fullname")
    private String fullName;

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}


package com.github.istin.schedule.gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yury on 15.10.2015.
 */
public class LecturerList {

    private Integer count;
    private List<LecturerModel> items;

    public LecturerList() {
        items = new ArrayList<LecturerModel>();
    }

    public LecturerList(Integer count, List<LecturerModel> lecturerModels) {
        this.count = count;
        this.items = lecturerModels;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<LecturerModel> getLecturers() {
        return items;
    }

    public void setLecturers(List<LecturerModel> lecturerModels) {
        this.items = lecturerModels;
    }

    @Override
    public String toString() {
        return count.toString();
    }

}


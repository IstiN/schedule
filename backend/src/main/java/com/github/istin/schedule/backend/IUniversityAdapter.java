package com.github.istin.schedule.backend;

import com.github.istin.schedule.gson.LecturerModel;

import java.util.List;

/**
 * Created by uladzimir_klyshevich on 10/12/15.
 */
public interface IUniversityAdapter {

    List<LecturerModel> getLecturerList() throws Exception;

}

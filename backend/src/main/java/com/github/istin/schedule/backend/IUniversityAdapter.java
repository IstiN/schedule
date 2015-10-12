package com.github.istin.schedule.backend;

import com.github.istin.schedule.backend.gson.Lector;

import java.util.List;

/**
 * Created by uladzimir_klyshevich on 10/12/15.
 */
public interface IUniversityAdapter {

    List<Lector> getLectorList() throws Exception;

}

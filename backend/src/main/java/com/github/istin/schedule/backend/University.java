package com.github.istin.schedule.backend;

import com.github.istin.schedule.backend.adapter.grsu.GrsuAdapter;

/**
 * Created by uladzimir_klyshevich on 10/12/15.
 */
public enum University {

    GRSU("ГрГУ", new GrsuAdapter()),
    GR_MED_UNIVER("Гродненский Мед. университет", new GrsuAdapter());

    private String mName;

    private IUniversityAdapter mUniversityAdapter;

    University(String pName, IUniversityAdapter pUniversityAdapter) {
        mName = pName;
        mUniversityAdapter = pUniversityAdapter;
    }

    public String getName() {
        return mName;
    }

    public IUniversityAdapter getUniversityAdapter() {
        return mUniversityAdapter;
    }
}

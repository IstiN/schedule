package com.github.istin.schedule.gson;

import java.io.Serializable;

/**
 * Created by uladzimir_klyshevich on 10/12/15.
 */
public class BaseModel implements Comparable<BaseModel>, Serializable {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String pId) {
        id = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(BaseModel o) {
        return toString().compareTo(o.toString());
    }
}

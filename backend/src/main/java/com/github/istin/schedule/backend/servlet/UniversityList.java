/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.github.istin.schedule.backend.servlet;

import com.github.istin.schedule.gson.University;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UniversityList extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        List<University> universities = new ArrayList<>();
        for (com.github.istin.schedule.backend.University university : com.github.istin.schedule.backend.University.values()) {
            University universityModel = new University();
            universityModel.setId(String.valueOf(university.ordinal()));
            universityModel.setName(university.getName());
            universities.add(universityModel);
        }
        Collections.sort(universities);
        resp.getWriter().print(new Gson().toJson(universities));
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        throw new IllegalArgumentException("post is not supported");
    }
}

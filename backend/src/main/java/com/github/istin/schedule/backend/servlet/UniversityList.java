/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.github.istin.schedule.backend.servlet;

import com.github.istin.schedule.backend.University;
import com.github.istin.schedule.backend.gson.UniversityModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
        List<UniversityModel> universityModels = new ArrayList<>();
        for (University university : University.values()) {
            UniversityModel universityModel = new UniversityModel();
            universityModel.setId(university.ordinal());
            universityModel.setName(university.getName());
            universityModels.add(universityModel);
        }
        resp.getWriter().print(new Gson().toJson(universityModels));
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        throw new IllegalArgumentException("post is not supported");
    }
}

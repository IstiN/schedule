/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.github.istin.schedule.backend.servlet;

import com.github.istin.schedule.backend.University;
import com.github.istin.schedule.backend.gson.Lector;
import com.github.istin.schedule.backend.gson.UniversityModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LecturerList extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        final String id = req.getParameter("id");
        final University university = University.values()[Integer.valueOf(id)];
        List<Lector> lectorList = null;
        try {
            lectorList = university.getUniversityAdapter().getLectorList();
            resp.getWriter().print(new Gson().toJson(lectorList));
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        throw new IllegalArgumentException("post is not supported");
    }
}

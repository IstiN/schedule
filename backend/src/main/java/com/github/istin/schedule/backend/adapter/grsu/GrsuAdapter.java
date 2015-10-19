package com.github.istin.schedule.backend.adapter.grsu;

import com.github.istin.schedule.backend.IUniversityAdapter;
import com.github.istin.schedule.backend.adapter.grsu.domain.LecturerResponse;
import com.github.istin.schedule.backend.adapter.grsu.domain.LecturerModel;
import com.github.istin.schedule.backend.utils.HttpUtils;
import com.github.istin.schedule.gson.Lecturer;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by uladzimir_klyshevich on 10/12/15.
 */
public class GrsuAdapter implements IUniversityAdapter {

    public static final String BASE_URL_API = "http://api.grsu.by/1.x/app1/";
    public static final String LECTURER_LIST_API = BASE_URL_API + "getTeachers?extended=false";

    @Override
    public List<Lecturer> getLecturerList() throws Exception {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = HttpUtils.getInputStream(LECTURER_LIST_API);
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader, 8192);
            final LecturerResponse lecturerList = new Gson().fromJson(bufferedReader, LecturerResponse.class);
            final List<LecturerModel> lecturers = lecturerList.getLecturers();
            List<Lecturer> list = new ArrayList<>();
            if (lecturers != null) {
                for (LecturerModel lecturerModel : lecturers) {
                    final Lecturer lecturer = new Lecturer();
                    lecturer.setId(lecturerModel.getId());
                    lecturer.setName(lecturerModel.getFullName());
                    list.add(lecturer);
                }
            }
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HttpUtils.close(inputStream);
            HttpUtils.close(inputStreamReader);
            HttpUtils.close(bufferedReader);
        }

        return new ArrayList<>();
    }
}
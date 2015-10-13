package com.github.istin.schedule.backend.adapter;

import com.github.istin.schedule.backend.IUniversityAdapter;
import com.github.istin.schedule.gson.Lecturer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uladzimir_klyshevich on 10/12/15.
 */
public class GrsuAdapter implements IUniversityAdapter {

    public static final String URL_LECTURERS = "http://raspisanie.grsu.by/TimeTable/UMUTeachers.aspx?Id=3";

    @Override
    public List<Lecturer> getLecturerList() throws Exception {
        Document doc = Jsoup.connect(URL_LECTURERS).get();
        Elements elements = doc.select("#DropDownList1 option");
        List<Lecturer> lecturers = new ArrayList<>();
        for (Element element : elements) {
            final String val = element.val();
            if ("0".equals(val)) {
                continue;
            }
            Lecturer lecturer = new Lecturer();
            lecturer.setId(val);
            lecturer.setName(element.text());
            lecturers.add(lecturer);
        }
        return lecturers;
    }

}

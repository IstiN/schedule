package com.github.istin.schedule.backend.adapter;

import com.github.istin.schedule.backend.IUniversityAdapter;
import com.github.istin.schedule.backend.gson.Lector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by uladzimir_klyshevich on 10/12/15.
 */
public class GrsuAdapter implements IUniversityAdapter {

    public static final String URL_LECTORS = "http://raspisanie.grsu.by/TimeTable/UMUTeachers.aspx?Id=3";

    @Override
    public List<Lector> getLectorList() throws Exception {
        Document doc = Jsoup.connect(URL_LECTORS).get();
        Elements elements = doc.select("#DropDownList1 option");
        List<Lector> lectors = new ArrayList<>();
        for (Element element : elements) {
            final String val = element.val();
            if ("0".equals(val)) {
                continue;
            }
            Lector lector = new Lector();
            lector.setId(val);
            lector.setName(element.text());
            lectors.add(lector);
        }
        return lectors;
    }

}

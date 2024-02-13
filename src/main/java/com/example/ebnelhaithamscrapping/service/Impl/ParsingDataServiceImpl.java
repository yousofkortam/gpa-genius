package com.example.ebnelhaithamscrapping.service.Impl;

import com.example.ebnelhaithamscrapping.model.Course;
import com.example.ebnelhaithamscrapping.service.ParsingDataService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParsingDataServiceImpl implements ParsingDataService {

    @Override
    public List<Course> getCourses(String page, List<Course> unannouncedDegrees) {
        List<Course> courses = new ArrayList<>();
        try {
            final Document document = Jsoup.parse(page);
            var coursesTable = document.select("table");
            for (int i = 1; i < coursesTable.size() - 5; i++) {
                var rows = coursesTable.get(i).select("tbody > tr");
                for (int j = 0; j < rows.size() - 1; j++) {
                    var data = rows.get(j).select("td");
                    String courseCode = data.get(0).text();
                    String courseName = data.get(1).text();
                    int creditHours = Integer.parseInt(data.get(2).text());
                    int degree = parseDegree(data.get(3).text());
                    if (degree != -1) {
                        String term = "Term " + i;
                        Course course = Course.builder()
                                .name(courseName)
                                .code(courseCode)
                                .creditHours(creditHours)
                                .term(term)
                                .degree(degree)
                                .grade("A")
                                .build();
                        courses.add(course);
                    }
                }
            }
            courses.addAll(unannouncedDegrees);
        } catch (Exception e) {
            System.out.println("Error : \n" + e.getMessage());
        }
        return courses;
    }

    private int parseDegree(String degreeStr) {
        try {
            return Integer.parseInt(degreeStr);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

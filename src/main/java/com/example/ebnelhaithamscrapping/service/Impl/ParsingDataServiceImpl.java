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
    public List<Course> getCourses(String page, List<Integer> unannouncedDegrees) {
        List<Course> courses = new ArrayList<>();
        try {
            final Document document = Jsoup.parse(page);
            var coursesTable = document.select("table");
            for (int i = 1; i < coursesTable.size() - 7; i++) {
                var tbody = coursesTable.get(i).select("tbody > tr");
                for (int j = 0; j < tbody.size() - 1; j++) {
                    var data = tbody.get(j).select("td");
                    String courseCode = data.get(0).text();
                    String courseName = data.get(1).text();
                    int creditHours = Integer.parseInt(data.get(2).text());
                    String degreeStr = data.get(3).text();
                    int degree;
                    try {
                        degree = Integer.parseInt(degreeStr);
                    }catch (Exception e) {
                        degree = -1;
                        System.out.println("Error : " + e.getMessage());
                    }
                    Course course = Course.builder()
                            .name(courseName)
                            .code(courseCode)
                            .creditHours(creditHours)
                            .degree(degree)
                            .grade("A")
                            .build();
                    courses.add(course);
                }
            }
            for (Integer unannouncedDegree : unannouncedDegrees) {
                Course course = Course.builder()
                        .name("Unannounced")
                        .code("Unannounced")
                        .creditHours(3)
                        .degree(unannouncedDegree)
                        .grade("A")
                        .build();
                courses.add(course);
            }
        } catch (Exception e) {
            System.out.println("Error : \n" + e.getMessage());
        }
        return courses;
    }
}

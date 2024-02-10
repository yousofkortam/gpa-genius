package com.example.ebnelhaithamscrapping.service;

import com.example.ebnelhaithamscrapping.model.Course;

import java.util.List;

public interface ParsingDataService {
    List<Course> getCourses(String page, List<Integer> unannouncedDegrees);
}

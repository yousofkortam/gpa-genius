package com.example.gpagenius.service;

import com.example.gpagenius.model.Course;

import java.util.List;

public interface ParsingDataService {
    List<Course> getCourses(String page, List<Course> unannouncedDegrees);
}

package com.example.ebnelhaithamscrapping.service;

import com.example.ebnelhaithamscrapping.model.Course;

import java.util.List;

public interface GPAService {
    double calcGPA(List<Course> grades, boolean optional);
    String convertGPA(double gpa);
}

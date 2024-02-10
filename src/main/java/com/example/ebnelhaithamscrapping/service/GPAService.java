package com.example.ebnelhaithamscrapping.service;

import com.example.ebnelhaithamscrapping.model.Course;

import java.util.List;

public interface GPAService {
    double calcGPA(List<Course> grades);
    String convertGPA(double gpa);
}

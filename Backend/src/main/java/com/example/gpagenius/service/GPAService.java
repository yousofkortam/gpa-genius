package com.example.gpagenius.service;

import com.example.gpagenius.model.Course;

import java.util.List;

public interface GPAService {
    double calcGPA(List<Course> grades);
    String convertGPA(double gpa);
}

package com.example.gpagenius.service.Impl;

import com.example.gpagenius.model.Course;
import com.example.gpagenius.service.GPAService;
import com.example.gpagenius.service.GradeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GPAServiceImpl implements GPAService {

    private final GradeConverter gradeConverter;

    @Override
    public double calcGPA(List<Course> grades) {
        Map<String, Course> courses = new HashMap<>();
        for (Course course : grades) {
            courses.put(course.getCode(), course);
        }
        double sumPoints = 0; int sumHours = 0;
        for (Map.Entry<String, Course> entry : courses.entrySet()) {
            if (entry.getValue().getDegree() != -1) {
                double point = getPoint(entry.getValue().getDegree());
                sumPoints += (point * entry.getValue().getCreditHours());
                sumHours += entry.getValue().getCreditHours();
            }
        }
        return Math.round((sumPoints / sumHours) * 100.0) / 100.0;
    }

    @Override
    public String convertGPA(double gpa) {
        return gradeConverter.getGrade(gpa);
    }

    public double getPoint(int grade) {
        if (grade < 50) {
            return 0;
        }else if (grade < 60) {
            return 2;
        }else if (grade < 65) {
            return 2.2;
        }else if (grade < 70) {
            return 2.4;
        }else if (grade < 75) {
            return 2.7;
        }else if (grade < 80) {
            return 3;
        }else if (grade < 85) {
            return 3.3;
        }else if (grade < 90) {
            return 3.7;
        }
        return 4;
    }

}

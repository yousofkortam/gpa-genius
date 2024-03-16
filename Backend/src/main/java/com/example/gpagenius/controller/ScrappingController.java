package com.example.gpagenius.controller;

import com.example.gpagenius.exception.GPAException;
import com.example.gpagenius.model.Course;
import com.example.gpagenius.model.Grade;
import com.example.gpagenius.model.Request;
import com.example.gpagenius.model.Response;
import com.example.gpagenius.service.GPAService;
import com.example.gpagenius.service.ParsingDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class ScrappingController {

    private final ParsingDataService parsingDataService;
    private final GPAService gpaService;

    @GetMapping("/")
    public String home() {
        return "SERVER IS WORKING!";
    }

    @PostMapping("/scrap-grades")
    public ResponseEntity<?> calculateGPAFCIMU(@RequestBody Request request) {
        List<Course> termCourses = getTermCourses(request.getGrades());
        List<Course> courses = parsingDataService.getCourses(request.getPage(), termCourses);

        if (courses.isEmpty()) {
            throw new GPAException("No courses found", HttpStatus.NOT_FOUND);
        }

        double termGPA = gpaService.calcGPA(termCourses),
                cumulativeGPA = gpaService.calcGPA(courses);
        String termGrade = gpaService.convertGPA(termGPA),
                cumulativeGrade = gpaService.convertGPA(cumulativeGPA);

        return ResponseEntity.ok(new Response(termGPA, termGrade, cumulativeGPA, cumulativeGrade));
    }

    private List<Course> getTermCourses(List<Grade> grades) {
        List<Course> termCourses = new ArrayList<>();
        if (grades != null && !grades.isEmpty()) {
            int index = 0;
            for (Grade grade : grades) {
                Course course = Course.builder()
                        .name("Unannounced Course " + index)
                        .code("UnannouncedCode" + index)
                        .creditHours(grade.getCreditHours())
                        .degree(grade.getGrade())
                        .grade("A")
                        .build();
                termCourses.add(course);
                index++;
            }
        }
        return termCourses;
    }
}

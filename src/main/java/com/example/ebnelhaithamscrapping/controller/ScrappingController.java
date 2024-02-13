package com.example.ebnelhaithamscrapping.controller;

import com.example.ebnelhaithamscrapping.model.Course;
import com.example.ebnelhaithamscrapping.model.Response;
import com.example.ebnelhaithamscrapping.service.GPAService;
import com.example.ebnelhaithamscrapping.service.ParsingDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class ScrappingController {

    private final ParsingDataService parsingDataService;
    private final GPAService gpaService;

    @PostMapping("/scrap-grades")
    public ResponseEntity<?> calculateGPAFCIMU(@RequestBody String page, @RequestParam(value = "grades", required = false) String grades) {
        String[] gradeArray;
        List<Integer> unannouncedGrades;
        List<Course> termCourses = new ArrayList<>();

        if (grades != null) {
            gradeArray = grades.split(",");
            unannouncedGrades = Arrays.stream(gradeArray)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            int index = 0;
            for (Integer unannouncedDegree : unannouncedGrades) {
                Course course = Course.builder()
                        .name("Unannounced")
                        .code("Unannounced"+index++)
                        .creditHours(3)
                        .degree(unannouncedDegree)
                        .grade("A")
                        .build();
                termCourses.add(course);
            }
        }

        List<Course> courses = parsingDataService.getCourses(page, termCourses);
        double termGPA = gpaService.calcGPA(termCourses, true),
                cumulativeGPA = gpaService.calcGPA(courses, false);
        String termGrade = gpaService.convertGPA(termGPA),
                cumulativeGrade = gpaService.convertGPA(cumulativeGPA);
        return ResponseEntity.ok(new Response(termGPA, termGrade, cumulativeGPA, cumulativeGrade));
    }
}

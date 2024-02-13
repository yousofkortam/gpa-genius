package com.example.gpagenius.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    double termGPA;
    String termGrade;
    double cumulativeGPA;
    String cumulativeGrade;
}

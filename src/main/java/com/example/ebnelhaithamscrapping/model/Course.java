package com.example.ebnelhaithamscrapping.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Course {
    String name;
    int degree;
    int creditHours;
    String term;
    String grade;
    String code;
}

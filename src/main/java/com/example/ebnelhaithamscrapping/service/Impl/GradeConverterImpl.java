package com.example.ebnelhaithamscrapping.service.Impl;

import com.example.ebnelhaithamscrapping.service.GradeConverter;
import org.springframework.stereotype.Service;

@Service
public class GradeConverterImpl implements GradeConverter {
    @Override
    public String getGrade(double gpa) {
        if (gpa < 1.5) return "ضعيف جدا";
        if (gpa < 2) return "ضعيف";
        if (gpa < 2.5) return "مقبول";
        if (gpa < 3) return "جيد";
        if (gpa < 3.5) return "جيد جدا";
        return "ممتاز";
    }
}

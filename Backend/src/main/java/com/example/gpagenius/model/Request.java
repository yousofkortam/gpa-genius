package com.example.gpagenius.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Request {
    private String page;
    private List<Grade> grades;
}

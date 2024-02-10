package com.example.ebnelhaithamscrapping.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GPAException extends RuntimeException {
    private final HttpStatus statusCode;
    public GPAException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
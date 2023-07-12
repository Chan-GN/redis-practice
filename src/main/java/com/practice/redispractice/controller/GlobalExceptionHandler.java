package com.practice.redispractice.controller;

import com.practice.redispractice.exception.MemberNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleMemberNotFoundException(MemberNotFoundException memberNotFoundException) {
        String errorMsg = memberNotFoundException.getMessage();

        return ResponseEntity.badRequest().body(errorMsg);
    }

}

package com.sassenach.backendexample.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ReadingNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(ReadingNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String employeeNotFoundHandler(ReadingNotFoundException ex) {
    return ex.getMessage();
  }
}
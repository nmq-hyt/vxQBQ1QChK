package com.sassenach.backendexample.errorhandling;

public class ReadingNotFoundException extends RuntimeException {

  public ReadingNotFoundException(Integer id) {
    super("Reading does not exist " + id);
  }
}

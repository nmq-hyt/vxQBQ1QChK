package com.sassenach.backendexample.errorhandling;

public class InvalidReadingException extends RuntimeException {
   
    public InvalidReadingException() {
    super("Invalid reading submission!");
    }

}
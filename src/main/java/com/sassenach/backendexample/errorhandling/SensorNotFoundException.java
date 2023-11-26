package com.sassenach.backendexample.errorhandling;

public class SensorNotFoundException extends RuntimeException {
  public SensorNotFoundException(String id) {
    super("Sensor name: " + id + "does not exist!");
  }


}

package com.sassenach.backendexample.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

// An entity representing an unique reading from a sensor.

@Entity
@Table(name ="readings")
public class Reading {
    
    // seconds since epoch format number.
    // will assume we're using UTC
    // timezones are a huge difficulty and suffice to say for this program's sake
    // i will not go into them here
    
    private Long timestamp;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer readingNumber;
    private Date dateTimeStamp;
    private Integer temperatureMetricCelsius;
    private Integer humiditiyPercentage;
    private Integer windspeedKmPerHour;
    private String deviceName;
    
    
    public Reading() {
    }

    public void setdateTimeStamp(Date dateTimeStamp) {
        this.dateTimeStamp = dateTimeStamp;
    }



    public Reading(Long timestamp,  Integer temperatureMetricCelsius,
            Integer humiditiyPercentage, Integer windspeedKmPerHour, String deviceName) {
        this.timestamp = timestamp;
        this.dateTimeStamp = new Date(timestamp);
        this.temperatureMetricCelsius = temperatureMetricCelsius;
        this.humiditiyPercentage = humiditiyPercentage;
        this.windspeedKmPerHour = windspeedKmPerHour;
        this.deviceName = deviceName;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getTemperatureMetricCelsius() {
        return temperatureMetricCelsius;
    }
    public void setTemperatureMetricCelsius(Integer temperatureMetricCelsius) {
        this.temperatureMetricCelsius = temperatureMetricCelsius;
    }   
    public Integer getHumiditiyPercentage() {
        return humiditiyPercentage;
    }
    public void setHumiditiyPercentage(Integer humiditiyPercentage) {
        this.humiditiyPercentage = humiditiyPercentage;
    }
    public Integer getWindspeedKmPerHour() {
        return windspeedKmPerHour;
    }
    public void setWindspeedKmPerHour(Integer windspeedKmPerHour) {
        this.windspeedKmPerHour = windspeedKmPerHour;
    }

    public Integer getReadingNumber() {
        return readingNumber;
    }

    public void setReadingNumber(Integer readingNumber) {
        this.readingNumber = readingNumber;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Date getDateTimeStamp() {
        return dateTimeStamp;
    }


}

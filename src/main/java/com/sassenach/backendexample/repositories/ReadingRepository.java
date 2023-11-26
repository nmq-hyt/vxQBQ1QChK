package com.sassenach.backendexample.repositories;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sassenach.backendexample.entities.Reading;

/**
 * ReadingRepository
 */
public interface ReadingRepository extends JpaRepository<Reading,Integer> {

    @Query("SELECT r FROM Reading r WHERE r.deviceName = :deviceName")
    Double findAllReadingsFromSensor();

    @Query("SELECT AVG(r.temperatureMetricCelsius) FROM  Reading r WHERE r.deviceName = :deviceName AND r.dateTimeStamp BETWEEN :startDate AND :endDate")
    Double getAverageTemperature(Date startDate, Date endDate, String deviceName);

    @Query("SELECT MAX(r.temperatureMetricCelsius) FROM Reading r WHERE r.deviceName = :deviceName AND r.dateTimeStamp BETWEEN :startDate AND :endDate")
    Double getMaxTemperature(Date startDate, Date endDate, String deviceName);

    @Query("SELECT MIN(r.temperatureMetricCelsius) FROM Reading r WHERE r.deviceName = :deviceName AND r.dateTimeStamp BETWEEN :startDate AND :endDate")
    Double getMinTemperature(Date startDate, Date endDate, String deviceName);

    @Query("SELECT SUM(r.temperatureMetricCelsius) FROM Reading r WHERE r.deviceName = :deviceName AND r.dateTimeStamp BETWEEN :startDate AND :endDate")
    Double getSumTemperature(Date startDate, Date endDate, String deviceName);

}
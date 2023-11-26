package com.sassenach.backendexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sassenach.backendexample.entities.Sensor;

/**
 * SensorRepository
 */
public interface SensorRepository extends JpaRepository<Sensor,String>{
    

    
}
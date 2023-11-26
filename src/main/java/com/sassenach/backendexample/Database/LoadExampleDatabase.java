package com.sassenach.backendexample.Database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sassenach.backendexample.entities.Reading;
import com.sassenach.backendexample.entities.Sensor;
import com.sassenach.backendexample.repositories.ReadingRepository;
import com.sassenach.backendexample.repositories.SensorRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initReadingDatabase(ReadingRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Reading(1700944131000L,23,5,11,"Jeremiah")));
      log.info("Preloading " + repository.save(new Reading(1700944131010L,21,13,5,"Sacred")));

    };
  }

  @Bean
  CommandLineRunner initSensorDatabase(SensorRepository repository) {
        return args -> {
      log.info("Preloading " + repository.save(new Sensor("32","Jeremiah")));
      log.info("Preloading " + repository.save(new Sensor("45","Sacred")));

    };
  }
}

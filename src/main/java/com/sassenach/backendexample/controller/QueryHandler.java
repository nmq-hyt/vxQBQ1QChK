package com.sassenach.backendexample.controller;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sassenach.backendexample.entities.Query;
import com.sassenach.backendexample.entities.Response;
import com.sassenach.backendexample.entities.Reading;
import com.sassenach.backendexample.entities.Sensor;
import com.sassenach.backendexample.errorhandling.InvalidQueryException;
import com.sassenach.backendexample.errorhandling.ReadingNotFoundException;
import com.sassenach.backendexample.errorhandling.SensorNotFoundException;
import com.sassenach.backendexample.repositories.ReadingRepository;
import com.sassenach.backendexample.repositories.SensorRepository;


@RestController
public class QueryHandler {

    private final SensorRepository sensorRepository;
    private final SensorAssembler sensorAssembler;
    private final ReadingRepository repository;
    private final ReadingAssembler assembler;
    

public QueryHandler(SensorRepository sensorRepository, SensorAssembler sensorAssembler,
            ReadingRepository repository, ReadingAssembler assembler) {
        this.sensorRepository = sensorRepository;
        this.sensorAssembler = sensorAssembler;
        this.repository = repository;
        this.assembler = assembler;
    }


@GetMapping("/sensors")
CollectionModel<EntityModel<Sensor>> all() {
     List<EntityModel<Sensor>> sensors = sensorRepository.findAll().stream()
      .map(sensorAssembler::toModel) //
      .collect(Collectors.toList());

  return CollectionModel.of(sensors, linkTo(methodOn(QueryHandler.class).all()).withSelfRel());
    
}

// just give me the last reading on a sensor
@GetMapping("/sensors/{id}/getlatest")

EntityModel<Reading> latestReadingFromSensor(@PathVariable String id) {
    Sensor sensor = sensorRepository.findById(id)
        .orElseThrow(() -> new SensorNotFoundException(id));

        Reading latest = repository.findAll().stream()
        .filter(r -> (Objects.equals(r.getDeviceName(),sensor.getDeviceName())))
        .reduce((first,second) -> second)
        .get();

        return assembler.toModel(latest);
}

@PostMapping("/sensors/query")
ResponseEntity<ArrayList<Response>> rangeReadingsStatistics( @RequestBody Query query) {
    // if (!Stream.of(query).allMatch(Objects::isNull)) throw new InvalidQueryException();
    
    ArrayList<Response> responses = new ArrayList<Response>();
    for (String sensorString : query.getSensors()) {
        Sensor sensor = sensorRepository.findById(sensorString)
        .orElseThrow(() -> new SensorNotFoundException(sensorString));

        Double avgTemperature = repository.getAverageTemperature(query.getStartDate(), query.getEndDate(), sensor.getDeviceName());
        Double sumTemperature = repository.getSumTemperature(query.getStartDate(), query.getEndDate(), sensor.getDeviceName());
        Double minTemperature = repository.getMinTemperature(query.getStartDate(), query.getEndDate(), sensor.getDeviceName());
        Double maxTemperature = repository.getMaxTemperature(query.getStartDate(), query.getEndDate(), sensor.getDeviceName());


        Response response = new Response(avgTemperature, sumTemperature, minTemperature, maxTemperature);
        responses.add(response);


    }

    return ResponseEntity.ok(responses);

}

// add a reading
@PostMapping(value = "/sensors/addreading", 
consumes = {"*/*"})
ResponseEntity<?> sendNewReading(@RequestBody Reading newReading) {
    EntityModel<Reading> entityModel = assembler.toModel(repository.save(newReading));

    return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
}
    
}
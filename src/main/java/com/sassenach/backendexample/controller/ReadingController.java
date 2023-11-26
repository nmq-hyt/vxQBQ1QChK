package com.sassenach.backendexample.controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.apache.catalina.connector.Response;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sassenach.backendexample.entities.Reading;
import com.sassenach.backendexample.errorhandling.ReadingNotFoundException;
import com.sassenach.backendexample.repositories.ReadingRepository;

@RestController
class ReadingController {

  private final ReadingRepository repository;
  private final ReadingAssembler assembler;
  ReadingController(ReadingAssembler assembler,ReadingRepository repository) {
    this.repository = repository;
    this.assembler = assembler;
  }


@GetMapping("/Readings")
CollectionModel<EntityModel<Reading>> all() {

  List<EntityModel<Reading>> employees = repository.findAll().stream()
      .map(assembler::toModel) //
      .collect(Collectors.toList());

  return CollectionModel.of(employees, linkTo(methodOn(ReadingController.class).all()).withSelfRel());
}

  @PostMapping("/Readings")
  ResponseEntity<?> newReading(@RequestBody Reading newReading) {
    EntityModel<Reading> entityModel = assembler.toModel(repository.save(newReading));

    return ResponseEntity
          .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
          .body(entityModel);
  }

  // Single item

  @GetMapping(value ="/Readings/{id}",
  consumes = MediaType.ALL_VALUE)
  EntityModel<Reading> one(@PathVariable Integer id) {
    
    Reading reading = repository.findById(id)
      .orElseThrow(() -> new ReadingNotFoundException(id));
 
        return assembler.toModel(reading);  
  }


  @DeleteMapping("/Readings/{id}")
  void deleteReading(@PathVariable Integer id) {
    repository.deleteById(id);
  }
}
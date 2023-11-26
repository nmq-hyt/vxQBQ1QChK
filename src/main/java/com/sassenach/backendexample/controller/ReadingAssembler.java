package com.sassenach.backendexample.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.sassenach.backendexample.entities.Reading;

@Component
class ReadingAssembler implements RepresentationModelAssembler<Reading, EntityModel<Reading>> {

  @Override
  public EntityModel<Reading> toModel(Reading reading) {

    return EntityModel.of(reading, //
        linkTo(methodOn(ReadingController.class).one(reading.getReadingNumber())).withSelfRel(),
        linkTo(methodOn(ReadingController.class).all()).withRel("readings"));
  }
}


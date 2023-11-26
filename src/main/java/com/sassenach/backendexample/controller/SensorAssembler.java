package com.sassenach.backendexample.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.sassenach.backendexample.entities.Sensor;

@Component
class SensorAssembler implements RepresentationModelAssembler<Sensor, EntityModel<Sensor>> {

  @Override
  public EntityModel<Sensor> toModel(Sensor sensor) {

    return EntityModel.of(sensor,
        linkTo(methodOn(QueryHandler.class).latestReadingFromSensor(sensor.getDeviceName())).withSelfRel(),
        linkTo(methodOn(QueryHandler.class).all()).withRel("sensor"));
  }
}


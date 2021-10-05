package com.example.demo.service;

import com.example.demo.entity.Person;

import java.util.Optional;


public interface PersonaService {
    Iterable<Person> listarPerson();

    String savePerson(Person person);

    String updatePerson(Person person);

    Optional<Person> searchPerson(String dni);

    Optional<Person> searchCar(String plate);


}

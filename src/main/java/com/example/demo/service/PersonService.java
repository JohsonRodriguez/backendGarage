package com.example.demo.service;

import com.example.demo.entity.Person;

import java.util.Optional;


public interface PersonService {
    Iterable<Person> listarPerson();

     void newPerson(Person person);

     void updatePerson(Person person);
     Long countPerson();

    Optional<Person> searchPerson(String dni);





}

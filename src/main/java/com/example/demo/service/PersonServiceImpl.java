package com.example.demo.service;

import com.example.demo.repository.PersonRepository;
import com.example.demo.entity.Person;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Iterable<Person> listarPerson() {
        return personRepository.findAll();
    }

    public void newPerson(Person person) {
        var checkPerson = personRepository.findById(person.getDni());
        if (!checkPerson.isPresent()) {
              personRepository.save(person);
        } else {
            throw new UserNotFoundException("DNI ya existe");
        }

    }

    @Override
    public void updatePerson(Person person) {
        var checkPerson = personRepository.findById(person.getDni());
        if (checkPerson.isPresent()) {
           Person p = new Person();
              personRepository.updatePerson(person.getName(),
                    person.getLastname(),
                    person.getPhone(),
                    person.getType(),
                    person.getState(),
                    person.getDni());
        } else {
                throw new UserNotFoundException("Person does'nt exists");
        }
    }

    @Override
    public Long countPerson() {
        return personRepository.count();
    }

    @Override
    public Optional<Person> searchPerson(String dni) {
        var checkPerson = personRepository.findById(dni);
        if (checkPerson.isPresent()) {
            return personRepository.findById(dni);
        } else {
            throw new UserNotFoundException("The dni: " + dni + " doesn't exists");
        }

    }



}
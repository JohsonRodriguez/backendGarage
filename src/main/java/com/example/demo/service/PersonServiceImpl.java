package com.example.demo.service;

import com.example.demo.Repository.PersonRepository;
import com.example.demo.entity.Person;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class PersonServiceImpl implements PersonaService {
    @Autowired
    PersonRepository personRepository;

    public Iterable<Person> listarPerson() {
        return personRepository.findAll();
    }

    public String savePerson(Person person) {
        var checkPerson = personRepository.findById(person.getDni());
        if (!checkPerson.isPresent()) {
            var checkCar = personRepository.findByRegistrationplate(person.getRegistrationplate());
            if (!checkCar.isPresent()) {
                personRepository.save(person);
            } else {
                throw new UserNotFoundException("Registration Plate exists");
            }
        } else {
            throw new UserNotFoundException("Person exists");
        }
        return "Person saved";
    }

    @Override
    public String updatePerson(Person person) {
        var checkPersonCar = personRepository.findByRegistrationplateAndAndDni(person.getRegistrationplate(), person.getDni());
        if (checkPersonCar.isPresent()) {
            personRepository.updatePerson(person.getName(),
                    person.getLastname(),
                    person.getRegistrationplate().toUpperCase(),
                    person.getCarbrand(),
                    person.getCarmodel(),
                    person.getCarcolor(),
                    person.getDni());
        } else {
            var checkCar = personRepository.findByRegistrationplate(person.getRegistrationplate());
            if (!checkCar.isPresent()) {
                personRepository.updatePerson(person.getName(),
                        person.getLastname(),
                        person.getRegistrationplate().toUpperCase(),
                        person.getCarbrand(),
                        person.getCarmodel(),
                        person.getCarcolor(),
                        person.getDni());
            } else {
                throw new UserNotFoundException("Registration Plate exists");
            }
        }

        return "Person updated";
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

    @Override
    public Optional<Person> searchCar(String plate) {
        String Upperplate=plate.toUpperCase();
        var checkCar=personRepository.findByRegistrationplate(Upperplate);
        if (checkCar.isPresent()) {
            return personRepository.findByRegistrationplate(Upperplate);
        } else {
            throw new UserNotFoundException("The dni: " + Upperplate + " doesn't exists");
        }
    }

}

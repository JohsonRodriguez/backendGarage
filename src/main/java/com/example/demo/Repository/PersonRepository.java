package com.example.demo.Repository;


import com.example.demo.entity.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, String> {
    Optional<Person> findFirstByDni(String dni);
    Optional<Person> findByRegistrationplate(String plate);
    Optional<Person>findByRegistrationplateAndAndDni(String plate,String dni);



    @Modifying
    @Transactional
    @Query("update Person p set p.name = :name, p.lastname = :lastname, p.registrationplate = :registrationplate, p.carbrand = :carbrand, p.carmodel = :carmodel, p.carcolor = :carcolor where p.dni = :dni")
    void  updatePerson(@Param(value = "name")String name,
                     @Param(value = "lastname")String lastname,
                     @Param(value = "registrationplate")String registrationplate,
                     @Param(value = "carbrand")String carbrand,
                     @Param(value = "carmodel")String carmodel,
                     @Param(value = "carcolor")String carcolor,
                     @Param(value = "dni")String dni);

}
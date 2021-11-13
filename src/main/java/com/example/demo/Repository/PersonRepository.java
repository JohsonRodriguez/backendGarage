package com.example.demo.repository;


import com.example.demo.entity.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, String> {
    Optional<Person> findFirstByDni(String dni);

    @Modifying
    @Transactional
    @Query("update Person p set p.name = :name, p.lastname = :lastname, p.phone = :phone, p.type = :type, p.state = :state where p.dni = :dni")
    void  updatePerson(@Param(value = "name")String name,
                       @Param(value = "lastname")String lastname,
                       @Param(value = "phone")String phone,
                       @Param(value = "type")String type,
                       @Param(value = "state")String state,
                       @Param(value = "dni")String dni);

}
package com.example.demo.repository;

import com.example.demo.entity.Car;
import com.example.demo.entity.Person;
import com.example.demo.entity.Register;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RegisterRepository extends CrudRepository<Register,Long> {

    List<Register>  findAllByCarAndDay(Car car, String day);
    List<Register> findAllByDay (String day);

}

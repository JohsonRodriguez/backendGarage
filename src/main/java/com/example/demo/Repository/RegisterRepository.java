package com.example.demo.Repository;

import com.example.demo.entity.Person;
import com.example.demo.entity.Register;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface RegisterRepository extends CrudRepository<Register,Long> {

    List<Register>  findAllByPersonAndDay(Person person, String day);
    List<Register> findAllByDay (String day);

}

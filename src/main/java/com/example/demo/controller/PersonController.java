package com.example.demo.controller;

import com.example.demo.QRGenerator.QRCodeGenerator;
import com.example.demo.entity.Person;
import com.example.demo.exception.PersonsNotFoundException;
import com.example.demo.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonServiceImpl personServiceImpl;

    @GetMapping("/all")
    public Iterable<Person> getAllPerson(){
         return personServiceImpl.listarPerson();
    }

    @PostMapping("/add")
    public void addPerson(@RequestBody Person person){
            personServiceImpl.savePerson(person);
    }
    @PutMapping("/update")
    public void updatePerson(@RequestBody Person person){
        personServiceImpl.updatePerson(person);
    }

    @GetMapping("find/{dni}")
    public Optional<Person> getById(@PathVariable("dni") String dni) {
       return personServiceImpl.searchPerson(dni);
    }

    @GetMapping("findcar/{plate}")
    public Optional<Person> getByCar(@PathVariable("plate") String plate) {
        return personServiceImpl.searchCar(plate);
    }
    @GetMapping(value = "/generateQRCode/{width}/{height}/{codeText}")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable("codeText") String codeText, @PathVariable("width") int width, @PathVariable("height") int height)throws Exception
    {
        return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRImage(codeText, width, height));
    }

}


package com.example.demo.controller;

import com.example.demo.qrgenerator.QRCodeGenerator;
import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    public Iterable<Person> getAllPerson(){
        return personService.listarPerson();
    }

    @PostMapping("/add")
    public void addPerson(@RequestBody Person person){
        personService.newPerson(person);
    }

    @PutMapping("/update")
    public void updatePerson(@RequestBody Person person){
        personService.updatePerson(person);
    }

    @GetMapping("find/{dni}")
    public Optional<Person> getById(@PathVariable("dni") String dni) {
        return personService.searchPerson(dni);
    }

    @GetMapping("/count")
    public Long countPerson(){
        return personService.countPerson();
    }

    @GetMapping(value = "/generateQRCode/{width}/{height}/{codeText}")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable("codeText") String codeText, @PathVariable("width") int width, @PathVariable("height") int height)throws Exception
    {
        return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRImage(codeText, width, height));
    }

}

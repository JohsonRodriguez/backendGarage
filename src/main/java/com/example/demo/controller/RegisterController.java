package com.example.demo.controller;

import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.Register;
import com.example.demo.service.RegisterService;
import com.example.demo.service.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

      @PostMapping("/save")
    public void saveRegister(@Valid @RequestBody RegisterDto registerDto) {
        registerService.saveRegister(registerDto);
    }

//    @PutMapping(value = "/checkout", produces = "application/json")
//    public void updateRegister(@RequestParam Long idRegister){
//        registerService.updateRegister(idRegister);
//    }

    @PostMapping(value = "/checkout/{registrationplate}")
    public void  updateRegister(@PathVariable("registrationplate") String registrationplate){
         registerService.updateRegister(registrationplate);
    }

    @GetMapping("/searchday/{day}")
    public List<Register>registerDiary(@PathVariable("day") String day){
       return registerService.searchDay(day);
    }

    @GetMapping("/count")
    public Long countRegister(){
        return registerService.countRegistro();
    }

    @GetMapping("/searchplate/{registrationplate}")
    public List<Register>searchRegistersPerson(@PathVariable("registrationplate") String registrationplate){
        return registerService.searchCarbyDay(registrationplate);
    }
}

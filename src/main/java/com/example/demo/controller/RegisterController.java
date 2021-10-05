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

    @Autowired
    private RegisterServiceImpl registerServiceImpl;

    @PostMapping("/save")
    public void saveRegister(@Valid @RequestBody RegisterDto registerDto) {
        registerService.saveRegister(registerDto);
    }

//    @PutMapping(value = "/checkout", produces = "application/json")
//    public void updateRegister(@RequestParam Long idRegister){
//        registerService.updateRegister(idRegister);
//    }

    @PostMapping(value = "/checkout/{dni}")
    public void  updateRegister(@PathVariable("dni") String dni){
         registerService.updateRegister(dni);
    }

    @GetMapping("/searchday/{day}")
    public List<Register>registerDiary(@PathVariable("day") String day){
       return registerServiceImpl.searchDay(day);
    }

    @GetMapping("/searchdni/{dni}")
    public List<Register>searchRegistersPerson(@PathVariable("dni") String dni){
        return registerServiceImpl.searchPersonbyDay(dni);
    }
}

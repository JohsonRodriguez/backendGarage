package com.example.demo.controller;

import com.example.demo.entity.Car;
import com.example.demo.entity.Person;
import com.example.demo.service.CarService;
import com.example.demo.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    //inyectar x Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public Iterable<Car> getAllCars(){
        return carService.listCar();
    }

    @PostMapping("/add")
    public void addCar(@RequestBody Car car){
        carService.newCar(car);
    }

    @PutMapping("/update")
    public void updateCar(@RequestBody Car car){
        carService.updateCar(car);
    }
    @GetMapping("/count")
    public Long countCar(){
        return carService.countCar();
    }

    @GetMapping("findcar/{plate}")
    public Optional<Car> getByCar(@PathVariable("plate") String plate) {
        return carService.searchCar(plate);
    }

}

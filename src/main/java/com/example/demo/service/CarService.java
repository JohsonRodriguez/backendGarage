package com.example.demo.service;

import com.example.demo.entity.Car;


import java.util.Optional;


public interface CarService {
    Iterable<Car> listCar();
    void newCar(Car car);
    void updateCar(Car car);
    Long countCar();
    Optional<Car> searchCar(String plate);
}

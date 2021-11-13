package com.example.demo.service;

import com.example.demo.entity.Car;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.CarRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Iterable<Car> listCar() {
        return carRepository.findAll();
    }

    @Override
    public void newCar(Car car) {
        var checkCarr= carRepository.findById(car.getRegistrationplate());
        if(!checkCarr.isPresent()){
            carRepository.save(car);
        }else{
            throw new UserNotFoundException("Car exists");
        }
   }

    @Override
    public void updateCar(Car car) {
             carRepository.save(car);
    }

    @Override
    public Long countCar() {
        return carRepository.count();
    }

    @Override
    public Optional<Car> searchCar(String plate) {
        String Upperplate=plate.toUpperCase();
        var checkCar=carRepository.findByRegistrationplate(Upperplate);
        if (checkCar.isPresent()) {
            return carRepository.findByRegistrationplate(Upperplate);
        } else {
            throw new UserNotFoundException("La placa " + Upperplate + " no esta registrada");
        }
    }
}

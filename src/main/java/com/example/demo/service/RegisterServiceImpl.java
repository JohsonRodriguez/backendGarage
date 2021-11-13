package com.example.demo.service;


import com.example.demo.repository.CarRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.RegisterRepository;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.Register;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CarRepository carRepository;

    @Override
    public void saveRegister(RegisterDto registerDto) {
        var day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        var checkinHour = new SimpleDateFormat("HH:mm:ss").format(new Date());
        var register = new Register();
        var car = carRepository.findByRegistrationplate(registerDto.getRegistrationplate().toUpperCase());
        if (car.isPresent()) {
            var registros = registerRepository.findAllByCarAndDay(car.get(), day);
            if (registros.size() > 0) {
                var ultimoRegistro = registros.get(registros.size() - 1);
                if (ultimoRegistro.getCheckout() != null) {
                    register.setCar(car.get());
                    register.setCheckin(checkinHour);
                    register.setUserid(registerDto.getUser());
                    register.setObs("ingreso correcto");
                    register.setDay(day);
                    registerRepository.save(register);
                } else {
                    throw new UserNotFoundException("Vehiculo ya ingreso, registre su salida primero");
                }
            }else{
                register.setCar(car.get());
                register.setCheckin(checkinHour);
                register.setUserid(registerDto.getUser());
                register.setObs("ingreso correcto");
                register.setDay(day);
                registerRepository.save(register);
            }
        } else {
            throw new UserNotFoundException("Error en registro, vehiculo no existe");
        }

    }
    ////////
//    @Override
//    public void saveRegister(RegisterDto registerDto) {
//        var day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        var checkinHour = new SimpleDateFormat("HH:mm:ss").format(new Date());
//        var register = new Register();
//        var person = personRepository.findFirstByDni(registerDto.getDni());
//        if (person.isPresent()) {
//            register.setPerson(person.get());
//            register.setCheckin(checkinHour);
//            register.setUserid(registerDto.getUser());
//            register.setObs("ingreso correcto");
//            register.setDay(day);
//        } else {
//            throw new UserNotFoundException("Checkin error, person doesn't exists");
//        }
//        registerRepository.save(register);
//    }
    //////////

    @Override
    public void updateRegister(String registrationplate) {
        var car = carRepository.findByRegistrationplate(registrationplate.toUpperCase());
        if (car.isPresent()) {
            var day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            var registros = registerRepository.findAllByCarAndDay(car.get(), day);
            if (registros.size() > 0) {
                var ultimoRegistro = registros.get(registros.size() - 1);
                if (ultimoRegistro.getCheckout() == null) {
                    var checkoutHour = new SimpleDateFormat("HH:mm:ss").format(new Date());
                    ultimoRegistro.setCheckout(checkoutHour);
                    registerRepository.save(ultimoRegistro);
                } else {
                    throw new UserNotFoundException("Vehiculo ya registro salida");
                }
            } else {
                throw new UserNotFoundException("Vehiculo aun no ha ingresado");
            }


        } else {
            throw new UserNotFoundException("Person dosen't exists");
        }
//        var registro = registerRepository.findById(id);
//        if (registro.isPresent()) {
//            var checkoutHour = new SimpleDateFormat("HH:mm:ss").format(new Date());
//            var r = registro.get();
//            if (r.getCheckout() == null) {
//                r.setCheckout(checkoutHour);
//                registerRepository.save(r);
//            } else {
//                throw new UserNotFoundException("Checkout already registe");
//            }
//        } else {
//            throw new UserNotFoundException("El id: " + id + " no existe");
//        }
    }

    /////////
//@Override
//public void updateRegister(Long id) {
//    var registro = registerRepository.findById(id);
//    if (registro.isPresent()) {
//        var checkoutHour = new SimpleDateFormat("HH:mm:ss").format(new Date());
//        var r = registro.get();
//        if (r.getCheckout() == null) {
//            r.setCheckout(checkoutHour);
//            registerRepository.save(r);
//        } else {
//            throw new UserNotFoundException("Checkout already registe");
//        }
//    } else {
//        throw new UserNotFoundException("El id: " + id + " no existe");
//    }
//}
    //////////
    @Override
    public List<Register> searchDay(String day) {
        return registerRepository.findAllByDay(day);
    }

    @Override
    public Long countRegistro() {
        return registerRepository.count();
    }

    @Override
    public List<Register> searchCarbyDay (String registrationplate) {
        var car = carRepository.findByRegistrationplate(registrationplate);
        if (car.isPresent()) {
            var day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            return registerRepository.findAllByCarAndDay(car.get(), day);
        }else {
             throw new UserNotFoundException("Person doesn't exists");
        }
    }

}

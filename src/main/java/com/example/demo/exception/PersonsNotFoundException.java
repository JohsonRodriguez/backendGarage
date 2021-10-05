package com.example.demo.exception;

public class PersonsNotFoundException extends RuntimeException{
    public PersonsNotFoundException(String message){
        super(message);
    }
}

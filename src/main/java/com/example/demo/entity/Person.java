package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "person")
public class Person  implements Serializable {
    @Id
    private String dni;
    private String name;
    private String lastname;
    private String registrationplate;
    private String carbrand;
    private String carmodel;
    private String carcolor;


}

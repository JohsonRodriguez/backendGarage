package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Car implements Serializable {
    @Id
    private String registrationplate;
    private String carbrand;
    private String carmodel;
    private String carcolor;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_dni")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Person person;
}

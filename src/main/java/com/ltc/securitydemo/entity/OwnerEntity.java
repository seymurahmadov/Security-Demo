package com.ltc.securitydemo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "owner")
@Getter
@Setter
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private Integer age;


    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "ownerEntity")
    @JsonIgnore
    private List<CarEntity> carEntity;

}

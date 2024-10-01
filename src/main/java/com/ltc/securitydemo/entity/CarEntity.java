package com.ltc.securitydemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ltc.securitydemo.security.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "car")
@Getter
@Setter
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String carName;

    @Column(name = "car_year")
    private Integer carYear;


    @OneToOne
    private EngineEntity engineEntity;

    @ManyToOne
    @JoinColumn(name= "owner_id")
    @JsonIgnore
    private OwnerEntity ownerEntity;

    @ManyToMany
    @JoinTable(
            name = "car_wheel",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<WheelEntity> wheelEntityList;


    @ManyToOne
    @JoinColumn(name= "user_id")
    @JsonIgnore
    private UserEntity userEntity;

}

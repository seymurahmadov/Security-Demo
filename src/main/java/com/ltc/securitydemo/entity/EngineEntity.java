package com.ltc.securitydemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "engine")
@Getter
@Setter
public class EngineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number")
    private Integer serialNumber;

    private String model;
    private String type;

    @Column(name = "horse_power")
    private Integer horsePower;


    @OneToOne
    private CarEntity carEntity;
}

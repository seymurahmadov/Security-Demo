package com.ltc.securitydemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "wheel")
@Getter
@Setter
public class WheelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name")
    private String brandName;

    private Integer size;
    private Integer radius;


    @ManyToMany(mappedBy = "wheelEntityList")
    private List<CarEntity> carEntityList;
}

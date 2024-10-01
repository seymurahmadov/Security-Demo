package com.ltc.securitydemo.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestDto {

    private String carName;
    private Integer carYear;
//    private Long ownerId;
    private Long userId;


}

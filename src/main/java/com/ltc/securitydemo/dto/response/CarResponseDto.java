package com.ltc.securitydemo.dto.response;

import com.ltc.securitydemo.entity.OwnerEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponseDto {
    private Long id;
    private String carName;
    private Integer carYear;
    private OwnerEntity ownerEntity;

}

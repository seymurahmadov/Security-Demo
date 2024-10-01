package com.ltc.securitydemo.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ltc.securitydemo.entity.CarEntity;
import com.ltc.securitydemo.enumaration.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "userEntity")
    @JsonIgnore
    private List<CarEntity> carEntity;


}

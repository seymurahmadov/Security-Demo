package com.ltc.securitydemo.repository;

import com.ltc.securitydemo.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository <OwnerEntity, Long> {
}

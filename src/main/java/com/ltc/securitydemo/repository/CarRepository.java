package com.ltc.securitydemo.repository;

import com.ltc.securitydemo.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    List<CarEntity> findAllByCarName(String carName);
    List<CarEntity> findAllByCarYearBetween(int from, int to);

    @Query("SELECT c FROM CarEntity c WHERE c.carYear > :minYear")
    List<CarEntity> findProductsAbovePrice(@Param("minYear") Integer minYear);

}

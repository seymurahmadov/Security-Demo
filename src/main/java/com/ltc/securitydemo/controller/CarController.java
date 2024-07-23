package com.ltc.securitydemo.controller;

import com.ltc.securitydemo.dto.request.CarRequestDto;
import com.ltc.securitydemo.dto.response.CarResponseDto;
import com.ltc.securitydemo.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {


    // 1. Field Injection
    // 2. Constructor Injection
    // 3. Setter Injection


    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/getAll")
    public List<CarResponseDto> getAll() {
        return carService.getAll();
    }


    @GetMapping("find-by-{id}")
    public CarResponseDto findById(@PathVariable Long id) {
        CarResponseDto byId = carService.findById(id);
        return byId;

    }

    @PostMapping("/create")
    public String create(@RequestBody CarRequestDto carDto) {
        carService.create(carDto);
        return "Success";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Long id) {
        carService.delete(id);
        return "Success";

    }


    @PutMapping("/update{id}")
    public String update(@PathVariable Long id, @RequestBody CarRequestDto carDto) {
        carService.update(id, carDto);
        return "Success";

    }


    @PostMapping("find-by-name{carName}")
    public List<CarResponseDto> findByName(@PathVariable String carName) {
       return carService.getAllByName(carName);
    }


}

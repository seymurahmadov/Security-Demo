package com.ltc.securitydemo.controller;

import com.ltc.securitydemo.dto.request.OwnerRequestDto;
import com.ltc.securitydemo.dto.response.OwnerResponseDto;
import com.ltc.securitydemo.service.OwnerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/getAll")
    public List<OwnerResponseDto> getAll() {
        return ownerService.getAll();
    }



    @PostMapping("/create")
    public String create(@RequestBody OwnerRequestDto requestDto) {
        ownerService.create(requestDto);
        return "Success";
    }

}

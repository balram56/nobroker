package com.nobroker.nobroker.controller;

import com.nobroker.nobroker.payload.OwnerPlanDto;
import com.nobroker.nobroker.service.OwnerPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ownerplan")
public class OwnerPlanController {

    @Autowired
    private OwnerPlanService ownerPlanService;
    //to save the plan in db
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OwnerPlanDto> createOwnerPlan(
            @RequestBody OwnerPlanDto ownerPlanDto ){
        OwnerPlanDto dto = ownerPlanService.createOwnerPlans(ownerPlanDto);
        return  new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //Read the plan all
    //http://localhost:8080/api/ownerplan
    @GetMapping
    public ResponseEntity<List<OwnerPlanDto>> getAllOwnerPlans(){
        List<OwnerPlanDto> dtoList = ownerPlanService.getAllOwnerPlans();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}

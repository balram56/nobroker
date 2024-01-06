package com.nobroker.nobroker.service.impl;

import com.nobroker.nobroker.entity.OwnerPlan;
import com.nobroker.nobroker.payload.OwnerPlanDto;
import com.nobroker.nobroker.repository.OwnerPlanRepository;
import com.nobroker.nobroker.service.OwnerPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OwnerPlanServiceImpl implements OwnerPlanService {
    private OwnerPlanRepository ownerPlanRepository;

    private ModelMapper modelMapper;

    public OwnerPlanServiceImpl(OwnerPlanRepository ownerPlanRepository, ModelMapper modelMapper) {
        this.ownerPlanRepository = ownerPlanRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OwnerPlanDto createOwnerPlans(OwnerPlanDto ownerPlanDto) {
        OwnerPlan ownerPlan = mapToEntity(ownerPlanDto);
        OwnerPlan savedOwnerPlan = ownerPlanRepository.save(ownerPlan);
        return mapToDto(savedOwnerPlan);

    }
    //method convert Dto to entity object
OwnerPlan mapToEntity(OwnerPlanDto ownerPlanDto){
    OwnerPlan ownerPlan = modelMapper.map(ownerPlanDto, OwnerPlan.class);
    return ownerPlan;
    }

    //method convert Dto to entity object
    OwnerPlanDto mapToDto(OwnerPlan ownerPlan){
        OwnerPlanDto ownerPlanDto = modelMapper.map(ownerPlan, OwnerPlanDto.class);
        return ownerPlanDto;
    }
}

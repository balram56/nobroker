package com.nobroker.nobroker.controller;

import com.nobroker.nobroker.service.OwnerPlanSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owner_plans_subs")
public class OwnerPlanSubscriptionController {

    private final OwnerPlanSubscriptionService ownerPlanSubscriptionService;

    @Autowired
    public OwnerPlanSubscriptionController(OwnerPlanSubscriptionService ownerPlanSubscriptionService) {
        this.ownerPlanSubscriptionService = ownerPlanSubscriptionService;
    }

    //for subscribe the plan
//http://localhost:8080/api/owner_plans_subs/subscribe/{userId}/{duration}
    @PostMapping("/subscribe/{userId}/{duration}")
    public ResponseEntity<Object> subscribeOwnerPlan(
            @PathVariable long userId,
            @PathVariable int duration
    ) {
        return ownerPlanSubscriptionService.subscribeOwnerPlan(userId, duration);
    }

    @GetMapping("/{ownerPlanId}")
    public ResponseEntity<Object> getOwnerPlan(@PathVariable long ownerPlanId) {
        return ownerPlanSubscriptionService.getSubscribeOwnerPlan(ownerPlanId);
    }
}

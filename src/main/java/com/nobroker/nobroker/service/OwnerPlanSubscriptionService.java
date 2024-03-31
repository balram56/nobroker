package com.nobroker.nobroker.service;
import com.nobroker.nobroker.entity.OwnerPlanSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface OwnerPlanSubscriptionService {
    public ResponseEntity<Object> subscribeOwnerPlan(long userId, int duration);

    public ResponseEntity<Object> getSubscribeOwnerPlan(long ownerPlanId);
}



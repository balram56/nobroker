package com.nobroker.nobroker.service.impl;

import com.nobroker.nobroker.entity.OwnerPlanSubscription;
import com.nobroker.nobroker.entity.User;
import com.nobroker.nobroker.exception.EntityNotFoundException;
import com.nobroker.nobroker.repository.OwnerPlanSubscriptionRepository;
import com.nobroker.nobroker.repository.UserRepository;
import com.nobroker.nobroker.service.OwnerPlanSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OwnerPlanSubscriptionServiceImpl implements OwnerPlanSubscriptionService {

    private final OwnerPlanSubscriptionRepository ownerPlanSubscriptionRepository;
    private final UserRepository userRepository;

    @Autowired
    public OwnerPlanSubscriptionServiceImpl(OwnerPlanSubscriptionRepository ownerPlanSubscriptionRepository, UserRepository userRepository) {
        this.ownerPlanSubscriptionRepository = ownerPlanSubscriptionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Object> subscribeOwnerPlan(long userId, int duration) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));

            OwnerPlanSubscription subscription = new OwnerPlanSubscription();
            subscription.setUserId(userId);
            subscription.setSubscriptionActive(true);
            subscription.setSubscriptionActiveDate(LocalDate.now());
            subscription.setSubscriptionExpirationDate(LocalDate.now().plusDays(duration));
            subscription.setNumberOfDays(duration);

            return ResponseEntity.status(HttpStatus.CREATED).body(ownerPlanSubscriptionRepository.save(subscription));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @Override
    public ResponseEntity<Object> getSubscribeOwnerPlan(long ownerPlanId) {
        try {
            OwnerPlanSubscription subscription = ownerPlanSubscriptionRepository.findById(ownerPlanId)
                    .orElseThrow(() -> new EntityNotFoundException("Subscription plan with id " + ownerPlanId + " not found"));

            return ResponseEntity.ok(subscription);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}


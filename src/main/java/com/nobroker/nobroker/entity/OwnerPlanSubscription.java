package com.nobroker.nobroker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "owner_plan_subscription")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerPlanSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ownerPlanId;
    @Column(name = "user_id", unique = true)
    private long userId;
    @Column(name = "subscription_active")
    private boolean subscriptionActive;
    @Column(name = "subscription_active_date")
    private LocalDate subscriptionActiveDate;
    @Column(name = "subscription_expiration_date")
    private LocalDate subscriptionExpirationDate;
    @Column(name= "number_of_days")
    private int numberOfDays;





}

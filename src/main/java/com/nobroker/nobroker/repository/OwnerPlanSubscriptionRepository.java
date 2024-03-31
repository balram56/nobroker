package com.nobroker.nobroker.repository;

import com.nobroker.nobroker.entity.OwnerPlanSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OwnerPlanSubscriptionRepository extends JpaRepository<OwnerPlanSubscription, Long> {
    List<OwnerPlanSubscription> findBySubscriptionExpirationDateBefore(LocalDate date);

    List<OwnerPlanSubscription> findExpiredSubscriptionsActiveTrueAndSubscriptionExpirationDateBefore(LocalDate now);
}



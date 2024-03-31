package com.nobroker.nobroker.schedular;

import com.nobroker.nobroker.entity.OwnerPlanSubscription;
import com.nobroker.nobroker.entity.User;
import com.nobroker.nobroker.exception.EntityNotFoundException;
import com.nobroker.nobroker.repository.OwnerPlanSubscriptionRepository;
import com.nobroker.nobroker.repository.UserRepository;
import com.nobroker.nobroker.service.OwnerPlanSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SubscriptionScheduler {

    private final JavaMailSender emailSender; // Assuming you have a JavaMailSender bean configured
    private final OwnerPlanSubscriptionRepository ownerPlanSubscriptionRepository;

    private final UserRepository userRepository;
    @Autowired
    public SubscriptionScheduler( UserRepository userRepository, JavaMailSender emailSender, OwnerPlanSubscriptionRepository ownerPlanSubscriptionRepository) {
        this.emailSender = emailSender;
        this.userRepository = userRepository;
        this.ownerPlanSubscriptionRepository = ownerPlanSubscriptionRepository;
    }

    @Scheduled(cron = "0 0 0 * * *") // Runs every day at midnight
    public void processExpiredSubscriptions() {
        List<OwnerPlanSubscription> expiredSubscriptions = ownerPlanSubscriptionRepository.findExpiredSubscriptionsActiveTrueAndSubscriptionExpirationDateBefore(LocalDate.now());

        for (OwnerPlanSubscription subscription : expiredSubscriptions) {
            subscription.setSubscriptionActive(false);
            ownerPlanSubscriptionRepository.save(subscription);

            // Send email notification to user
            sendEmailNotification(subscription);
        }
    }

    private void sendEmailNotification(OwnerPlanSubscription subscription) {
        String userEmail = getUserEmail(subscription.getUserId()); // Assuming you have a method to retrieve user email
        if (userEmail != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(userEmail);
            message.setSubject("Subscription Expired");
            message.setText("Your subscription has expired. Please renew it to continue accessing our services.");
            emailSender.send(message);
        }
    }

    // Method to retrieve user email by user ID
    private String getUserEmail(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));
        return user.getEmail();
    }


}

package org.softuni.nexpay.subscription.service;

import org.softuni.nexpay.subscription.entity.Subscription;
import org.softuni.nexpay.subscription.entity.SubscriptionPeriod;
import org.softuni.nexpay.subscription.entity.SubscriptionStatus;
import org.softuni.nexpay.subscription.entity.SubscriptionType;
import org.softuni.nexpay.subscription.repository.SubscriptionRepository;
import org.softuni.nexpay.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }


    public Subscription createNewSubscription(User user) {

        return subscriptionRepository.save(Subscription.builder()
                .type(SubscriptionType.DEFAULT)
                .period(SubscriptionPeriod.MONTHLY)
                .status(SubscriptionStatus.ACTIVE)
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .owner(user)
                .build());

    }
}

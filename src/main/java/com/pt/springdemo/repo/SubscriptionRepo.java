package com.pt.springdemo.repo;

import com.pt.springdemo.entity.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepo extends CrudRepository<Subscription, String> {
    Subscription findBySubscriptionId(String subscriptionId);
}
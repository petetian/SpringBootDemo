package com.microsoft.devcon.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.microsoft.devcon.demo.entity.Subscription;

public interface SubscriptionRepo extends CrudRepository<Subscription, String> {
    Subscription findBySubscriptionId(String subscriptionId);
}
package com.glovoCall.demo.Restaurants;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantsInterface extends JpaRepository<Restaurant, UUID>{
    // кароч у тебе тут був Restaurant, Long но в ростораны id це UUID поміняв кароч
    boolean existsByNameAndAddress(String name, String address);
}

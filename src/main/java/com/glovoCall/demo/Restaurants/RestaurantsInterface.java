package com.glovoCall.demo.Restaurants;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RestaurantsInterface extends JpaRepository<Restaurant, UUID>{
    // кароч у тебе тут був Restaurant, Long но в ростораны id це UUID поміняв кароч
    boolean existsByNameAndAddress(String name, String address);

    @Query("SELECT new com.glovoCall.demo.Restaurants.restaurantDTOResponse(MIN(r.id), r.name, MAX(r.imagePath)) " +
            "FROM Restaurant r GROUP BY r.name")
    List<restaurantDTOResponse> findAllUnique();
}

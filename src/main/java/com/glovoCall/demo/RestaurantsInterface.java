package com.glovoCall.demo;

import com.glovoCall.demo.Restaurants.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantsInterface extends JpaRepository<Restaurant,Long>{

}

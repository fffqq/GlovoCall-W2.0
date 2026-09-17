package com.glovoCall.demo.Restaurants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class RestaurantsService {
    private final RestaurantsInterface RestaurantRepo;

    public List<restaurantDTOResponse> ImageAndNameOfAllRest(){
        List<Restaurant> allRestaurants=RestaurantRepo.findAll();

        return allRestaurants.stream().map(restaurant -> new restaurantDTOResponse(
                        restaurant.getId(),
                        restaurant.getName(),
                        restaurant.getImagePath()
                        )
                )
                .toList();
    }
    public restaurantDTOResponse CreateRestaurant(restaurantDTORequest request){
        log.info("Try to save a Restaurant: name={},adress={}"
                ,request.name(),request.address());
        //logging saving the restaurant

        Restaurant restaurant=new Restaurant();
        restaurant.setName(request.name());
        restaurant.setAddress(request.address());
        //taking everything from DTO to Restaurant entity

        if (RestaurantRepo.existsByNameAndAddress(restaurant.getName(), restaurant.getAddress())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Restaurant already exist");
        }
        RestaurantRepo.save(restaurant);

        return new restaurantDTOResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress()
        );
    }




}

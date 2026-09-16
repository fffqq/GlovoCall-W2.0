package com.glovoCall.demo.Restaurants;

import com.glovoCall.demo.RestaurantsInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantsService {
    private final RestaurantsInterface RestaurantRepo;

    public List<RestaurantDTO> ImageAndNameOfAllRest(){
        List<Restaurant> allRestaurants=RestaurantRepo.findAll();

        return allRestaurants.stream().map(restaurant -> new RestaurantDTO(
                        restaurant.getId(),
                        restaurant.getName(),
                        restaurant.getImagePath()
                        )
                )
                .toList();
    }
    public void CreateRestaurant(Restaurant restaurant){
        if(restaurant == null){
            throw new IllegalArgumentException("restaurant is null. IDI NAHUI SUKA");
        }

        if (RestaurantRepo.existsByNameAndAddress(restaurant.getName(), restaurant.getAddress())) {
            throw new IllegalArgumentException("restaurant  already exist. IDI NAHUI");
        }
        RestaurantRepo.save(restaurant);
    }




}

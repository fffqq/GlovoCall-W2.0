package com.glovoCall.demo.Restaurants;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/GlovoCall")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class RestaurantsController {

    private final RestaurantsInterface RestaurantRepo;
    private final RestaurantsService restaurantsService;

    @GetMapping("/listOfRest")
    public List<restaurantDTOResponse> AllRestaurants(){
        return restaurantsService.ImageAndNameOfAllRest();
    }
    @PostMapping("/postRest")
    public void PostRest(
            @Valid
            @RequestBody
            restaurantDTORequest restaurant){
        restaurantsService.CreateRestaurant(restaurant);
    }
}

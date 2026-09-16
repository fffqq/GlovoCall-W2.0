package com.glovoCall.demo.Restaurants;


import com.glovoCall.demo.RestaurantsInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<RestaurantDTO> AllRestaurants(){
        return restaurantsService.ImageAndNameOfAllRest();
    }
    @PostMapping("/postRest")
    public void PostRest(
            @RequestBody
            @Valid
            Restaurant restaurant){
        restaurantsService.CreateRestaurant(restaurant);
    }
}

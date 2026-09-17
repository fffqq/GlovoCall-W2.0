package com.glovoCall.demo.Restaurants;

import jakarta.validation.constraints.NotBlank;

public record restaurantDTORequest
        (@NotBlank String name,@NotBlank String address){}

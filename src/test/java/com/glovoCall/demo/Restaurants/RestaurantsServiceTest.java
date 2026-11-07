package com.glovoCall.demo.Restaurants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantsServiceTest {

    @Mock
    private RestaurantsInterface restaurantRepo;

    @InjectMocks
    private RestaurantsService restaurantsService;

    @Test
    void createRestaurant_Success(){
        restaurantDTORequest request = new restaurantDTORequest("GOVNOED", "Gedimino pr. 1");
        when(restaurantRepo.existsByNameAndAddress(request.name(), request.address())).thenReturn(false);
        restaurantDTOResponse response = restaurantsService.CreateRestaurant(request);
        assertNotNull(response);
        assertEquals("GOVNOED", response.name());
        verify(restaurantRepo, times(1)).save(any(Restaurant.class));
    }

    @Test
    void createRestaurant_DuplicateThrowsException(){
        restaurantDTORequest request = new restaurantDTORequest("RASIAmama", "Shaman pr. 1");
        when(restaurantRepo.existsByNameAndAddress(request.name(), request.address())).thenReturn(true);
        assertThrows(ResponseStatusException.class, () -> restaurantsService.CreateRestaurant(request));
        verify(restaurantRepo, never()).save(any(Restaurant.class));
    }

}
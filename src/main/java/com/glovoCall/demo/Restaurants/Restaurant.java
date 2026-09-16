package com.glovoCall.demo.Restaurants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(
            strategy =GenerationType.UUID)
    private UUID id;
    @NotBlank
    @Column(nullable = false)
    private String name;
    @NotBlank
    private String address;
    @Column(unique = true)
    private String imagePath;



    public Restaurant(){

    }
}

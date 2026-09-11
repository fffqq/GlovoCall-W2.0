package com.glovoCall.demo.Restaurants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(
            strategy =GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    private String address;
    @Column(unique = true)
    private String imagePath;



    public Restaurant(){

    }
}

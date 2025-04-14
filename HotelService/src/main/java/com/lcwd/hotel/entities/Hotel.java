package com.emission.lcwd.hotel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "hotels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Hotel {

    @Id
    //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private String location;

    private String about;

}

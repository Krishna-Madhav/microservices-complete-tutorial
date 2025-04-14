package com.emission.lcwd.hotel.services;

import com.emission.lcwd.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    // create hotel
    Hotel addHotel(Hotel hotel);

    // get all
    List<Hotel> getHotels();

    // get one hotel
    Hotel getHotel(String id);

}
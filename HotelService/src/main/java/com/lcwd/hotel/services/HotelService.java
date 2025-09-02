package com.lcwd.hotel.services;

import com.lcwd.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    // create hotel
    Hotel addHotel(Hotel hotel);

    // get all hotels
    List<Hotel> getHotels();

    // get one hotel
    Hotel getHotel(String id);

}
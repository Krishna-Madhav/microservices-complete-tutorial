package com.emission.lcwd.hotel.services.impl;

import com.emission.lcwd.hotel.entities.Hotel;
import com.emission.lcwd.hotel.exceptions.ResourceNotFoundException;
import com.emission.lcwd.hotel.repositories.HotelRepository;
import com.emission.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel addHotel(Hotel hotel) {
        String hotelID = UUID.randomUUID().toString();
        hotel.setId(hotelID);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource with given hotel id not found!"));
    }
}
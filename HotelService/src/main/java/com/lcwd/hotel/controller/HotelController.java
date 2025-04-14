package com.emission.lcwd.hotel.controller;

import com.emission.lcwd.hotel.entities.Hotel;
import com.emission.lcwd.hotel.services.impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    HotelServiceImpl hotelService;

    // add new hotel
    @PostMapping
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel){
        Hotel addHotel = hotelService.addHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(addHotel);
    }

    // get all hotels
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        List<Hotel> hotelList = hotelService.getHotels();
        return ResponseEntity.ok(hotelList);
    }

    // get one hotel
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
    Hotel hotel = hotelService.getHotel(hotelId);
    return ResponseEntity.status(HttpStatus.OK).body(hotel);
    }
}

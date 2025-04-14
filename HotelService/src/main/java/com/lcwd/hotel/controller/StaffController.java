package com.emission.lcwd.hotel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    @GetMapping
    public ResponseEntity<List<String>> getEmployee(){

        List<String> list = Arrays.asList("Krishna", "Steve", "Brian", "Sachin");
        return new ResponseEntity<>(list, HttpStatus.OK);

    }
}

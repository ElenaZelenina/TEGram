package com.techelevator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/photos")
public class PhotoController {

    @PostMapping("/")
    public ResponseEntity<String> createPhoto() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
        // TODO come back to complete
    }
}

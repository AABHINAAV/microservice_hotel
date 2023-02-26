package com.examMS.HotelMS.Controllers;

import com.examMS.HotelMS.Entities.Hotel;
import com.examMS.HotelMS.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/createHotel")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel res = this.hotelService.createHotel(hotel);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/getAllHotels")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> allHotels = this.hotelService.getAllHotels();
        return new ResponseEntity<>(allHotels, HttpStatus.OK);
    }

    @GetMapping("/getHotelByHotelId/{hotelId}")
    public ResponseEntity<Hotel> getHotelByHotelId(@PathVariable("hotelId") String hotelId) {
        Hotel res = this.hotelService.getHotelByHotelId(hotelId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/deleteHotelByHotelId/{hotelId}")
    public ResponseEntity<String> deleteHotelByHotelId(@PathVariable("hotelId") String hotelId) {
        this.hotelService.deleteHotelByHotelId(hotelId);
        return new ResponseEntity<>("Hotel Deleted Successfully !!", HttpStatus.OK);
    }
}

package com.examMS.HotelMS.Services;

import com.examMS.HotelMS.Entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {
    public Hotel createHotel(Hotel hotel);

    public List<Hotel> getAllHotels();

    public Hotel getHotelByHotelId(String hotelId);

    public void deleteHotelByHotelId(String hotelId);
}

package com.examMS.HotelMS.Services.ServiceImplementations;

import com.examMS.HotelMS.Entities.Hotel;
import com.examMS.HotelMS.Exceptions.ResourceNotFoundException;
import com.examMS.HotelMS.Repositories.HotelRepository;
import com.examMS.HotelMS.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return this.hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelByHotelId(String hotelId) {
        return this.hotelRepository.findById(hotelId).orElseThrow(
                () -> new ResourceNotFoundException("No such hotel present with hotel id : " + hotelId)
        );
    }

    @Override
    public void deleteHotelByHotelId(String hotelId) {
        this.getHotelByHotelId(hotelId);
        this.hotelRepository.deleteById(hotelId);
    }
}

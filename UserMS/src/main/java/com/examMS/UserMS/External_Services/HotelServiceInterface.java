package com.examMS.UserMS.External_Services;

import com.examMS.UserMS.Entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelServiceInterface {

    @GetMapping("/hotels/getHotelByHotelId/{hotelId}")
    public Hotel getHotelByHotelId(@PathVariable("hotelId") String hotelId);
}

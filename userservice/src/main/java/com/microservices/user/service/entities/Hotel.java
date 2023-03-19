package com.microservices.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    private String hotelId;
    private String name;
    private String location;
    private String about;

    public String toString(Hotel hotel) {
        return "hotelId : "+ hotel.getHotelId()+
                " hotel name : "+ hotel.getName()+
                " hotel location : "+ hotel.getLocation()+
                " about hotel : "+ hotel.getAbout();
    }
}

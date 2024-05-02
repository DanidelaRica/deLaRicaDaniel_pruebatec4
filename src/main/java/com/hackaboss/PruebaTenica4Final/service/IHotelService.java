package com.hackaboss.PruebaTenica4Final.service;



import com.hackaboss.PruebaTenica4Final.model.Hotel;

import java.util.List;

public interface IHotelService {
    public List<Hotel> getAllHoteles();
    public Hotel getHotelById(Long id);
    public Hotel createHotel(Hotel hotel);
    public Hotel updateHotel(Long id, Hotel hotel);
    public void deleteHotel(Long id);
}

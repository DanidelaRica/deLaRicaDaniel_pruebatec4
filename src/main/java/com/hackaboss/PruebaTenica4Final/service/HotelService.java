package com.hackaboss.PruebaTenica4Final.service;


import com.hackaboss.PruebaTenica4Final.exception.ConflictException;
import com.hackaboss.PruebaTenica4Final.model.Hotel;
import com.hackaboss.PruebaTenica4Final.repository.HotelRepository;
import com.hackaboss.PruebaTenica4Final.repository.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService{
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Hotel> getAllHoteles() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel with id " + id + " not found"));
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(Long id, Hotel hotel) {
        if (!hotelRepository.existsById(id)) {
            throw new EntityNotFoundException("Hotel with id " + id + " not found");
        }
        hotel.setId(id);
        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotel(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new EntityNotFoundException("Hotel with id " + id + " not found");
        }

        if (reservaRepository.existsByHabitacion_Hotel_Id(id)) {
            throw new ConflictException("The hotel cannot be deleted because it is currently reserved.");
        }

        hotelRepository.deleteById(id);
    }


}

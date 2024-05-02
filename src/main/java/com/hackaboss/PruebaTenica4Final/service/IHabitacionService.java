package com.hackaboss.PruebaTenica4Final.service;



import com.hackaboss.PruebaTenica4Final.model.Habitacion;

import java.time.LocalDate;
import java.util.List;

public interface IHabitacionService {
    public List<Habitacion> getAllHabitaciones();
    public Habitacion getHabitacionById(Long id);
    public Habitacion createHabitacion(Habitacion habitacion);
    public Habitacion updateHabitacion(Long id, Habitacion habitacion);
    public void deleteHabitacion(Long id);
    public List<Habitacion> getAvailableRooms(LocalDate dateFrom, LocalDate dateTo, String destination);
    public boolean isRoomAvailableInDateRange(Habitacion habitacion, LocalDate dateFrom, LocalDate dateTo);
    public Habitacion getHabitacionByCodigoAndTipo(String codigo, String tipo);
}

package com.hackaboss.PruebaTenica4Final.service;



import com.hackaboss.PruebaTenica4Final.model.Habitacion;
import com.hackaboss.PruebaTenica4Final.model.Reserva;


import java.time.LocalDate;
import java.util.List;

public interface IReservaService {
    public List<Reserva> getAllReservas();
    public Reserva getReservaById(Long id);
    public Reserva createReserva(Reserva reserva);
    public Reserva updateReserva(Long id, Reserva reserva);
    public void deleteReserva(Long id);
    public double bookRoom(Reserva reserva);

}

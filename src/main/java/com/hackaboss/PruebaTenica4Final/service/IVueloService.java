package com.hackaboss.PruebaTenica4Final.service;



import com.hackaboss.PruebaTenica4Final.model.Vuelo;

import java.time.LocalDate;
import java.util.List;

public interface IVueloService {
    public List<Vuelo> getAllVuelos();
    public Vuelo getVueloById(Long id);
    public Vuelo createVuelo(Vuelo vuelo);
    public Vuelo updateVuelo(Long id, Vuelo vuelo);
    public void deleteVuelo(Long id);
    public List<Vuelo> getAvailableFlights(LocalDate dateFrom, LocalDate dateTo, String origin, String destination);
    public double bookFlight(int cantidadPersonas, String origen, String destino, LocalDate fechaIda);
}

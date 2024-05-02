package com.hackaboss.PruebaTenica4Final;

import com.hackaboss.PruebaTenica4Final.exception.NoHotelFoundException;
import com.hackaboss.PruebaTenica4Final.model.Hotel;
import com.hackaboss.PruebaTenica4Final.repository.HotelRepository;
import com.hackaboss.PruebaTenica4Final.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class HotelServiceTest {
    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllHotelesWhenHotelesExist() {
        // Arrange
        List<Hotel> hoteles = new ArrayList<>();
        hoteles.add(new Hotel(1L, "H001", "Hotel A", "Ciudad A"));
        hoteles.add(new Hotel(2L, "H002", "Hotel B", "Ciudad B"));

        when(hotelRepository.findAll()).thenReturn(hoteles);

        // Act
        List<Hotel> result = hotelService.getAllHoteles();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Hotel 1", result.get(0).getNombre());
        assertEquals("Hotel 2", result.get(1).getNombre());
    }

    @Test
    public void testGetAllHotelesWhenNoHotelesExist() {
        // Arrange
        when(hotelRepository.findAll()).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(NoHotelFoundException.class, () -> {
            hotelService.getAllHoteles();
        });
    }
}

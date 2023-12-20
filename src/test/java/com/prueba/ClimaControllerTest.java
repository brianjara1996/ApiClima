package com.prueba;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.prueba.apiclima.Controllers.ClimaController;
import com.prueba.apiclima.Dao.ClimaService;
import com.prueba.apiclima.Models.DatosClima;

public class ClimaControllerTest {


    private ClimaService climaService;
    private ClimaController climaController;

    @BeforeEach
    public void setup() {
        climaService = mock(ClimaService.class);
        climaController = new ClimaController();
    }

    @Test
    public void testTiempoById_ValidCodPostal_ReturnsOK() {
        int codPostal = 12345;
        DatosClima mockDatosClima = new DatosClima(); // Simula un objeto DatosClima

        // Define el comportamiento simulado del servicio ClimaService
        when(climaService.ClimaCodPostal(codPostal)).thenReturn(mockDatosClima);

        // Llama al método del controlador
        ResponseEntity<DatosClima> response = climaController.tiempoById(codPostal);

        // Verifica si se devuelve un código 200 (OK) y el objeto DatosClima
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDatosClima, response.getBody());
    }

    @Test
    public void testTiempoById_InvalidCodPostal_ReturnsInternalServerError() {
        int codPostal = 99999;

        // Define el comportamiento simulado del servicio ClimaService para un código postal inválido
        when(climaService.ClimaCodPostal(codPostal)).thenReturn(null);

        // Llama al método del controlador
        ResponseEntity<DatosClima> response = climaController.tiempoById(codPostal);

        // Verifica si se devuelve un código 500 (InternalServerError)
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testTiempoById_ExceptionThrown_ReturnsBadRequest() {
        int codPostal = 54321;

        // Define el comportamiento simulado del servicio ClimaService para lanzar una excepción
        when(climaService.ClimaCodPostal(codPostal)).thenThrow(new RuntimeException());

        // Llama al método del controlador
        ResponseEntity<DatosClima> response = climaController.tiempoById(codPostal);

        // Verifica si se devuelve un código 400 (BadRequest)
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
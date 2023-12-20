package com.prueba;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import com.prueba.apiclima.Dao.ClimaServiceImpl;
import com.prueba.apiclima.Models.DatosClima;

public class ClimaServiceImplTest {

    private ClimaServiceImpl climaService;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        climaService = new ClimaServiceImpl();
        restTemplate = mock(RestTemplate.class);  
    }

    @Test
    public void testClimaCodPostal_ValidCodPostal_ReturnsDatosClima() {
        int codPostal = 1824;
        String jsonResponseUbicacionKey = "[{\"Key\":\"504218_PC\",\"EnglishName\":\"Gerli (Pdo. Lanus)/}]";
        String jsonResponseTiempo = "[{\"Temperature\":{\"Metric\":{\"Value\":21.4,\"Unit\":\"C\",\"UnitType\":17}";

        // Simula la respuesta de la API de ubicación por código postal
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponseUbicacionKey);

        // Simula la respuesta de la API de tiempo
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponseTiempo);

        // Llama al método del servicio ClimaServiceImpl
        DatosClima datosClima = climaService.ClimaCodPostal(codPostal);

        // Verifica que se obtenga un objeto DatosClima
        assertNotNull(datosClima);

        
        // Verifica si se llaman a los métodos getForObject del RestTemplate con los parámetros esperados
        verify(restTemplate, times(1)).getForObject(anyString(), eq(String.class));
    }

    @Test
    public void testClimaCodPostal_ExceptionThrown_ReturnsNull() {
        int codPostal = 54321;

        // Simula que se lanza una excepción al llamar al RestTemplate
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenThrow(new RuntimeException());

        // Llama al método del servicio ClimaServiceImpl
        DatosClima datosClima = climaService.ClimaCodPostal(codPostal);

        // Verifica que se devuelva null en caso de excepción
        assertNull(datosClima);

        // Verifica si se llaman a los métodos getForObject del RestTemplate con los parámetros esperados
        verify(restTemplate, times(1)).getForObject(anyString(), eq(String.class));
    }
}
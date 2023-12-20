package com.prueba.apiclima.Dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.prueba.apiclima.Models.DatosClima;


@Repository
@Transactional
public class ClimaServiceImpl implements ClimaService {
	
	@Value("${data.tiempo.api.key}")
	private String apiKey;
	
	@Value("${api.url.tiempo}")
	private String urlTiempo;
	
	
	@Value("${api.url.codpostal}")
	private String urlCodPostal;
	
	
	 @PersistenceContext
	    EntityManager entityManager;
	 
	 
    //Metodo que Busca la UbicacionKey del codigo postal recibido y busca la temperatura de esa ciudad y lo Inserta en la Base de datos
	@Override
	public DatosClima ClimaCodPostal(Integer cod_postal) {
		try {
			
			DatosClima dt = new DatosClima();
			
			RestTemplate restTemplate = new RestTemplate();
			
			String Apiurl_codPostal = urlCodPostal + "?apikey=" + apiKey + "&q=" + cod_postal;
			
			//Invoco a la API de accuweather para consuntal la UbicacionKey del Codigo postal Recibido	
			String rpt_CodPostal = restTemplate.getForObject(Apiurl_codPostal, String.class);
			
			 //Parseo el resultado en formato Json para Recuperar las Variables que necesito y setearlas en DatosClima
            JSONArray json = new JSONArray(rpt_CodPostal);
            
            JSONObject obj = json.getJSONObject(0);
            
            dt.setUbucacionKey(obj.getString("Key"));
            
            dt.setCiudad(obj.getString("EnglishName"));
            
            dt.setCod_postal(cod_postal.toString());
            
            
            //Completo la urlTiempo con su ubicacionKey para poder obtener el clima en esa ubicacion
            urlTiempo = urlTiempo +  dt.getUbucacionKey() + "?apikey=" + apiKey;
            
            
           //Vuelvo a incovar  API de accuweather para obtener el clima de la ubicacion recibida
            String rpt_tiempo = restTemplate.getForObject(urlTiempo, String.class);
            
            json = new JSONArray(rpt_tiempo);
            
            obj = json.getJSONObject(0);
            
            //Parsea la respuesta y hace la validaciones para obtener finalmente la variable temperatura para poder setearlo en la clase DatosClima
            
            JSONObject temperature = obj.getJSONObject("Temperature");
            
            JSONObject Metric = temperature.getJSONObject("Metric");
            
            BigDecimal Temperatura = Metric.getBigDecimal("Value");         
           
            dt.setTemperatura(Temperatura.doubleValue());
            
            //una vez que se termina de setear todas las variables se pasa a la insercion en la Base de datos           
            entityManager.merge(dt);
            
			return dt;
		}
		catch(Exception e) {
			return null;
		}
	}
}

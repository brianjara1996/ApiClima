package com.prueba.apiclima.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.prueba.apiclima.Dao.ClimaService;
import com.prueba.apiclima.Models.DatosClima;

@RestController
@RequestMapping("/clima")
public class ClimaController {
	
	@Autowired
	private ClimaService climaService;
	
	@RequestMapping(value = "/tiempo-actual/{cod_postal}", method = RequestMethod.GET)
	public ResponseEntity<DatosClima> tiempoById(@PathVariable Integer  cod_postal){
		try {		
			DatosClima dt = new DatosClima();

			dt = climaService.ClimaCodPostal(cod_postal);
			
			if(dt != null) {
				return ResponseEntity.ok(dt);
			}
			else{
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}	
		}
		catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}

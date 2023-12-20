package com.prueba.apiclima.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

@Builder
@Entity
@Table(name="DatosClima")
public class DatosClima {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="ubucacionKey", nullable=false)
	private String ubucacionKey;
	
	@Column(name="ciudad", nullable=false)
    private String ciudad;
	
	@Column(name="temperatura", nullable=true)
    private double temperatura;
	
	@Column(name="cod_postal", nullable=false)
    private String cod_postal;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	public String getCod_postal() {
		return cod_postal;
	}
	public void setCod_postal(String cod_postal) {
		this.cod_postal = cod_postal;
	}
	public String getUbucacionKey() {
		return ubucacionKey;
	}
	public void setUbucacionKey(String ubucacionKey) {
		this.ubucacionKey = ubucacionKey;
	}
}

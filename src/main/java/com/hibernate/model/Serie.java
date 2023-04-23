package com.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/* 1 CREACIÓN DE MODELO HIBERNATE PARA ASOCIARLO A LA BD */

@Entity
@Table(name = "serie")
public class Serie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idserie")
	private int id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "temporadas")
	private int numTemp;
	@Column(name = "capitulos")
	private int numCapi;

	public Serie() {
		super();
	}

	public Serie(String nombre, int numTemp, int numCapi) {
		super();
		this.nombre = nombre;
		this.numTemp = numTemp;
		this.numCapi = numCapi;
	}

	@Override
	public String toString() {
		return "Serie [id=" + id + ", nombre=" + nombre + ", numTemp=" + numTemp + ", numCapi=" + numCapi + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumTemp() {
		return numTemp;
	}

	public void setNumTemp(int numTemp) {
		this.numTemp = numTemp;
	}

	public int getNumCapi() {
		return numCapi;
	}

	public void setNumCapi(int numCapi) {
		this.numCapi = numCapi;
	}

}

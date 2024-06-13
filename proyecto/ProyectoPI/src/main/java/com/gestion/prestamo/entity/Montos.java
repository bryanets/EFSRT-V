package com.gestion.prestamo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "montos")
public class Montos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idmonto")
	private int idMonto;

	@Column(name = "monto")
	private double monto;

	@Column(name = "dias")
	private int dias;

	@Column(name = "monto_interes")
	private double montoInteres;

	@JsonIgnore
	@OneToMany(mappedBy = "montos")
	private List<Prestamo> prestamos;

	public int getIdMonto() {
		return idMonto;
	}

	public void setIdMonto(int idMonto) {
		this.idMonto = idMonto;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public double getMontoInteres() {
		return montoInteres;
	}

	public void setMontoInteres(double montoInteres) {
		this.montoInteres = montoInteres;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

}

package com.gestion.prestamo.entity;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class RolEnlacePK {

	private int cod_rol;
	private int cod_enlace;
	
	@Override
	public int hashCode() {
		return Objects.hash(cod_enlace, cod_rol);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolEnlacePK other = (RolEnlacePK) obj;
		return cod_enlace == other.cod_enlace && cod_rol == other.cod_rol;
	}
	
	public int getCod_rol() {
		return cod_rol;
	}
	
	public void setCod_rol(int cod_rol) {
		this.cod_rol = cod_rol;
	}
	
	public int getCod_enlace() {
		return cod_enlace;
	}
	
	public void setCod_enlace(int cod_enlace) {
		this.cod_enlace = cod_enlace;
	}
	
}

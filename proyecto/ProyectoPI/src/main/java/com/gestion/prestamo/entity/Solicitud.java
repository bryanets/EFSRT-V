package com.gestion.prestamo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "solicitud")
public class Solicitud {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idsolicitud")
	private int idSolicitud;

	/*
	 * @Column(name = "monto")
	 * private double monto;
	 */

	@Column(name = "fecha")
	private Date fecha;

	/*
	 * @Column(name = "cuotas")
	 * private String cuotas;
	 */

	@Column(name = "estado")
	private String estado;

	@ManyToOne
	@JoinColumn(name = "idusuario")
	@JsonIgnoreProperties("solicitudes")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "idmonto")
	@JsonIgnoreProperties("prestamos")
	private Montos monto;

	public int getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	/*
	 * public double getMonto() {
	 * return monto;
	 * }
	 * 
	 * public void setMonto(double monto) {
	 * this.monto = monto;
	 * }
	 */

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/*
	 * public String getCuotas() {
	 * return cuotas;
	 * }
	 * 
	 * public void setCuotas(String cuotas) {
	 * this.cuotas = cuotas;
	 * }
	 */

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// Getters y setters

}

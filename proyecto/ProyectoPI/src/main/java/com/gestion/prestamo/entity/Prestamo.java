package com.gestion.prestamo.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "prestamo")
public class Prestamo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idprestamo")
	private int idPrestamo;

	@Column(name = "fecha_inicio")
	private Date fechai;

	@Column(name = "fecha_fin")
	private Date fechaf;

	/*
	 * @Column(name = "monto")
	 * private Double monto;
	 */

	@Column(name = "cuotas")
	private int cuotas;

	/*
	 * @Column(name = "totalpagar")
	 * private Double totalpagar;
	 */

	/*
	 * @Column(name = "importecuota")
	 * private Double importecuota;
	 */

	@Column(name = "pago_diario")
	private double pagoDiario;

	@Column(name = "estado")
	private String estado;

	/*
	 * @Column(name = "tasa_interes")
	 * private String tasa_interes;
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idprestatario")
	private Prestatario prestatario;

	@ManyToOne
	@JoinColumn(name = "idmonto")
	private Montos montos;

	@ManyToOne
	@JoinColumn(name = "idmodalidad")
	private Modalidad modalidad;

	@OneToMany(mappedBy = "prestamo")
	private List<DetallePrestamo> detallePrestamo;

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public Date getFechai() {
		return fechai;
	}

	public void setFechai(Date fechai) {
		this.fechai = fechai;
	}

	public Date getFechaf() {
		return fechaf;
	}

	public void setFechaf(Date fechaf) {
		this.fechaf = fechaf;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public double getPagoDiario() {
		return pagoDiario;
	}

	public void setPagoDiario(double pagoDiario) {
		this.pagoDiario = pagoDiario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Prestatario getPrestatario() {
		return prestatario;
	}

	public void setPrestatario(Prestatario prestatario) {
		this.prestatario = prestatario;
	}

	public Montos getMontos() {
		return montos;
	}

	public void setMontos(Montos montos) {
		this.montos = montos;
	}

	public List<DetallePrestamo> getDetallePrestamo() {
		return detallePrestamo;
	}

	public void setDetallePrestamo(List<DetallePrestamo> detallePrestamo) {
		this.detallePrestamo = detallePrestamo;
	}

}

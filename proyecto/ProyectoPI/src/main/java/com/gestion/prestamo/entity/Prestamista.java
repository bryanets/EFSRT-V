package com.gestion.prestamo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "prestamista")
public class Prestamista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idprestamista")
	private int idPrestamista;

	@ManyToOne
	@JoinColumn(name = "idgrupo")
	private Grupo grupo;

	@OneToOne
	@JoinColumn(name = "idusuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "idlocalcomercial")
	private LocalComercial localComercial;

	public int getIdPrestamista() {
		return idPrestamista;
	}

	public void setIdPrestamista(int idPrestamista) {
		this.idPrestamista = idPrestamista;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalComercial getLocalComercial() {
		return localComercial;
	}

	public void setLocalComercial(LocalComercial localComercial) {
		this.localComercial = localComercial;
	}

	/*
	 * public List<Prestamo> getPrestamos() {
	 * return prestamos;
	 * }
	 * 
	 * public void setPrestamos(List<Prestamo> prestamos) {
	 * this.prestamos = prestamos;
	 * }
	 */

	// Getters y setters

}

package com.gestion.prestamo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "inversionista")
public class Inversionista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idinversionista")
	private Integer idInversionista;

	@OneToOne
	@JoinColumn(name = "idusuario")
	private Usuario usuario;
	
	@JsonIgnore
	@OneToMany(mappedBy = "inversionista")
	private List<JefePrestamista> jefePrestamistas;

	public Integer getIdInversionista() {
		return idInversionista;
	}

	public void setIdInversionista(Integer idInversionista) {
		this.idInversionista = idInversionista;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<JefePrestamista> getJefePrestamistas() {
		return jefePrestamistas;
	}

	public void setJefePrestamistas(List<JefePrestamista> jefePrestamistas) {
		this.jefePrestamistas = jefePrestamistas;
	}
	
	
}
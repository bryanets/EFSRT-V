package com.gestion.prestamo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "jefeprestamista")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idJefePrestamista")
public class JefePrestamista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idjefeprestamista")
	private Integer idJefePrestamista;

	@ManyToOne
	@JoinColumn(name = "idinversionista")
	private Inversionista inversionista;

	@JsonIdentityReference(alwaysAsId = true)
	@OneToOne
	@JoinColumn(name = "idusuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "idgrupo")
	private Grupo grupo;

	public Integer getIdJefePrestamista() {
		return idJefePrestamista;
	}

	public void setIdJefePrestamista(Integer idJefePrestamista) {
		this.idJefePrestamista = idJefePrestamista;
	}

	public Inversionista getInversionista() {
		return inversionista;
	}

	public void setInversionista(Inversionista inversionista) {
		this.inversionista = inversionista;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	// Getters y setters
	
	
}
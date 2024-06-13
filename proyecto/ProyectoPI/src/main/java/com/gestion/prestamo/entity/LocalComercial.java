package com.gestion.prestamo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "localcomercial")
public class LocalComercial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlocalcomercial")
    private Integer idLocalComercial;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "grupo_idgrupo")
    private Grupo grupo;
    
    @JsonIgnore
    @OneToMany(mappedBy = "localComercial")
    private List<Prestamista> prestamistas;

    @JsonIgnore
    @OneToMany(mappedBy = "localComercial")
    private List<Prestatario> prestatarios;

	public Integer getIdLocalComercial() {
		return idLocalComercial;
	}

	public void setIdLocalComercial(Integer idLocalComercial) {
		this.idLocalComercial = idLocalComercial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Prestamista> getPrestamistas() {
		return prestamistas;
	}

	public void setPrestamistas(List<Prestamista> prestamistas) {
		this.prestamistas = prestamistas;
	}

	public List<Prestatario> getPrestatarios() {
		return prestatarios;
	}

	public void setPrestatarios(List<Prestatario> prestatarios) {
		this.prestatarios = prestatarios;
	}

    // Getters y setters
    
}

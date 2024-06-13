package com.gestion.prestamo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "grupo")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgrupo")
    private Integer idGrupo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private String estado;
    
    @JsonIgnore
    @OneToMany(mappedBy = "grupo")
    private List<JefePrestamista> jefesPrestamistas;

    @JsonIgnore
    @OneToMany(mappedBy = "grupo")
    private List<Prestamista> prestamistas;

    @JsonIgnore
    @OneToMany(mappedBy = "grupo")
    private List<LocalComercial> localesComerciales;

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<JefePrestamista> getJefesPrestamistas() {
		return jefesPrestamistas;
	}

	public void setJefesPrestamistas(List<JefePrestamista> jefesPrestamistas) {
		this.jefesPrestamistas = jefesPrestamistas;
	}

	public List<Prestamista> getPrestamistas() {
		return prestamistas;
	}

	public void setPrestamistas(List<Prestamista> prestamistas) {
		this.prestamistas = prestamistas;
	}

	public List<LocalComercial> getLocalesComerciales() {
		return localesComerciales;
	}

	public void setLocalesComerciales(List<LocalComercial> localesComerciales) {
		this.localesComerciales = localesComerciales;
	}

    // Getters y setters
    
}

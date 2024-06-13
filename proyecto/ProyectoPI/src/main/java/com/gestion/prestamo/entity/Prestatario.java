package com.gestion.prestamo.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "prestatario")
public class Prestatario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprestatario")
    private int idPrestatario;

    @ManyToOne
    @JoinColumn(name = "idlocalcomercial")
    private LocalComercial localComercial;

    @OneToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "prestatario")
    private List<Prestamo> prestamos;

	public int getIdPrestatario() {
		return idPrestatario;
	}

	public void setIdPrestatario(int idPrestatario) {
		this.idPrestatario = idPrestatario;
	}

	public LocalComercial getLocalComercial() {
		return localComercial;
	}

	public void setLocalComercial(LocalComercial localComercial) {
		this.localComercial = localComercial;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
}

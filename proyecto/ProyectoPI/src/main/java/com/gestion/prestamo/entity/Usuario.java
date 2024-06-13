package com.gestion.prestamo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idusuario")
	private Integer idUsuario;

	@Column(name = "nombres")
	private String nombres;

	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "password")
	private String password;

	@Column(name = "dni")
	private String dni;

	@Column(name = "correo")
	private String correo;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "direccion")
	private String direccion;

	@ManyToOne
	@JoinColumn(name = "rol_idrol")
	private Rol rol;

	@JsonIgnore
	@OneToOne(mappedBy = "usuario")
	private Inversionista inversionista;

	@JsonIgnore
	@OneToOne(mappedBy = "usuario")
	private JefePrestamista jefePrestamista;

	@JsonIgnore
	@OneToOne(mappedBy = "usuario")
	private Prestamista prestamista;

	@JsonIgnore
	@OneToOne(mappedBy = "usuario")
	private Prestatario prestatario;

	@OneToMany(mappedBy = "usuario")
	private List<Solicitud> solicitudes;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Inversionista getInversionista() {
		return inversionista;
	}

	public void setInversionista(Inversionista inversionista) {
		this.inversionista = inversionista;
	}

	public JefePrestamista getJefePrestamista() {
		return jefePrestamista;
	}

	public void setJefePrestamista(JefePrestamista jefePrestamista) {
		this.jefePrestamista = jefePrestamista;
	}

	public Prestamista getPrestamista() {
		return prestamista;
	}

	public void setPrestamista(Prestamista prestamista) {
		this.prestamista = prestamista;
	}

	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

}

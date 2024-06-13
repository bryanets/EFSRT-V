package com.gestion.prestamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.prestamo.entity.JefePrestamista;

public interface JefePrestamistaRepository extends JpaRepository<JefePrestamista, Integer> {
    @Query("SELECT COUNT(x) FROM JefePrestamista x WHERE x.usuario.correo = ?1")
    public Integer validarCorreo(String correo);

    @Query("SELECT COUNT(x) FROM JefePrestamista x WHERE x.usuario.usuario = ?1")
    public Integer validarUsuario(String usuario);

    @Query("SELECT COUNT(x) FROM JefePrestamista x WHERE x.usuario.dni = ?1")
    public Integer validarDni(String dni);

    @Query("SELECT COUNT(x) FROM JefePrestamista x WHERE x.usuario.telefono = ?1")
    public Integer validarTelefono(String telefono);

}

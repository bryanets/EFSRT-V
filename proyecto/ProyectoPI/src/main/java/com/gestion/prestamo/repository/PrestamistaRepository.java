package com.gestion.prestamo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.prestamo.entity.Prestamista;
import com.gestion.prestamo.entity.Prestatario;
import com.gestion.prestamo.entity.Solicitud;

public interface PrestamistaRepository extends JpaRepository<Prestamista, Integer> {

    @Query("SELECT X FROM Prestatario X WHERE X.localComercial.idLocalComercial=?1")
    public List<Prestatario> listaPrestatarios(int codigo);

    @Query("SELECT X FROM Solicitud X WHERE X.usuario.prestatario.localComercial.idLocalComercial=?1 ORDER BY fecha DESC")
    public List<Solicitud> listaSolicitudes(int codigo);

    @Query("SELECT X FROM Solicitud X WHERE X.usuario.prestatario.localComercial.idLocalComercial=?1 and X.usuario.apellidos=?2 ORDER BY fecha DESC")
    public List<Solicitud> listaSolicitudesXUsuario(int codigo, String apellidos);

    @Query("SELECT X FROM Solicitud X WHERE X.usuario.prestatario.localComercial.idLocalComercial = ?1 AND X.usuario.apellidos = ?2 AND DATE(X.fecha) >= DATE(?3) AND DATE(X.fecha) <= DATE(?4) ORDER BY X.fecha DESC")
    public List<Solicitud> listaSolicitudesXFecha(int codigo, String apellidos, Date desde, Date hasta);

    @Query("SELECT X FROM Solicitud X WHERE X.usuario.prestatario.localComercial.idLocalComercial = ?1 AND DATE(X.fecha)>=DATE(?2) AND DATE(X.fecha)  <= DATE(?3) ORDER BY X.fecha DESC")
    public List<Solicitud> listaSolicitudesXFechaSinPrestatario(int codigo, Date desde, Date hasta);
}

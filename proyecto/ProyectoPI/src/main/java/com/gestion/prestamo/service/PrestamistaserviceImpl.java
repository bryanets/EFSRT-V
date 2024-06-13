package com.gestion.prestamo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.prestamo.entity.Prestatario;
import com.gestion.prestamo.entity.Solicitud;
import com.gestion.prestamo.entity.Usuario;
import com.gestion.prestamo.repository.PrestamistaRepository;
import com.gestion.prestamo.repository.PrestarioRepository;
import com.gestion.prestamo.repository.UsuarioRepository;

@Service
public class PrestamistaserviceImpl implements PrestamistaService {

    @Autowired
    private PrestamistaRepository prestamistaRepository;

    @Autowired
    private PrestarioRepository prestatarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Prestatario> listaPrestatarios(int cod) {
        return prestamistaRepository.listaPrestatarios(cod);
    }

    @Override
    public Prestatario buscarPorId(int cod) {
        return prestatarioRepository.findById(cod).orElse(null);
    }

    @Transactional
    @Override
    public void guardar(Prestatario prestatario) {
        try {
            Usuario usuario = new Usuario();
            usuario = usuarioRepository.save(prestatario.getUsuario());
            prestatario.setUsuario(usuario);
            prestatarioRepository.save(prestatario);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void Eliminar(int cod) {
        prestatarioRepository.deleteById(cod);
    }

    @Override
    public Integer validarCorreo(String correo) {
        return usuarioRepository.validarCorreo(correo);
    }

    @Override
    public Integer validarUsuario(String correo) {
        return usuarioRepository.validarCorreo(correo);
    }

    @Override
    public Integer validarDni(String dni) {
        return usuarioRepository.validarDni(dni);
    }

    @Override
    public Integer validarTelefono(String telefono) {
        return usuarioRepository.validarTelefono(telefono);
    }

    @Override
    public List<Solicitud> listaSolicitudes(int cod) {
        return prestamistaRepository.listaSolicitudes(cod);
    }

    @Override
    public List<Solicitud> listaSolicitudesXUsuario(int cod, String apellidos) {
        return prestamistaRepository.listaSolicitudesXUsuario(cod, apellidos);
    }

    @Override
    public List<Solicitud> listaSolicitudesXFecha(int cod, String apellidos, Date desde, Date hasta) {
        return prestamistaRepository.listaSolicitudesXFecha(cod, apellidos, desde, hasta);
    }

    @Override
    public List<Solicitud> listaSolicitudesXFechaSinPrestatario(int cod, Date desde, Date hasta) {
        return prestamistaRepository.listaSolicitudesXFechaSinPrestatario(cod, desde, hasta);
    }

}

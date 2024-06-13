package com.gestion.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.prestamo.entity.Grupo;
import com.gestion.prestamo.entity.JefePrestamista;
import com.gestion.prestamo.entity.Usuario;
import com.gestion.prestamo.repository.GrupoRepository;
import com.gestion.prestamo.repository.JefePrestamistaRepository;
import com.gestion.prestamo.repository.UsuarioRepository;

@Service
public class InversionistaServiceImpl implements InversionistaService {
    @Autowired
    private JefePrestamistaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Override
    public List<JefePrestamista> jefePrestamistas() {
        return repository.findAll();
    }

    @Override
    public JefePrestamista buscarPorId(int cod) {
        return repository.findById(cod).orElse(null);
    }

    public JefePrestamista buscarPorUsuario(int cod) {
        return repository.findById(cod).orElse(null);
    }

    @Transactional
    @Override
    public void guardar(JefePrestamista jefePrestamista, Integer idGrupo) {
        try {
            Usuario usuario = new Usuario();
            usuario = usuarioRepository.save(jefePrestamista.getUsuario());
            jefePrestamista.setUsuario(usuario);
            Grupo grupo = new Grupo();

            if (idGrupo != null) {
                grupo = grupoRepository.findById(idGrupo).orElse(null);

                grupo.setEstado("disponible");
                grupoRepository.save(grupo);
                grupo = grupoRepository.findById(jefePrestamista.getGrupo().getIdGrupo()).orElse(null);
                grupo.setEstado("ocupado");
                grupoRepository.save(grupo);
            }

            jefePrestamista = repository.save(jefePrestamista);

            if (jefePrestamista.getGrupo() != null) {
                grupo = grupoRepository.findById(jefePrestamista.getGrupo().getIdGrupo()).orElse(null);
                grupo.setEstado("ocupado");
                grupoRepository.save(grupo);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void Eliminar(int cod) {
        repository.deleteById(cod);
    }

    @Override
    public Integer validarCorreo(String correo) {
        return repository.validarCorreo(correo);
    }

    @Override
    public Integer validarUsuario(String usuario) {
        return repository.validarUsuario(usuario);
    }

    @Override
    public Integer validarDni(String dni) {
        return repository.validarDni(dni);
    }

    @Override
    public Integer validarTelefono(String telefono) {
        return repository.validarTelefono(telefono);
    }

}

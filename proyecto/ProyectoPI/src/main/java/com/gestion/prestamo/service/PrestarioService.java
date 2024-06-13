package com.gestion.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.prestamo.entity.Grupo;
import com.gestion.prestamo.entity.JefePrestamista;
import com.gestion.prestamo.entity.Prestatario;
import com.gestion.prestamo.entity.Usuario;
import com.gestion.prestamo.repository.GrupoRepository;
import com.gestion.prestamo.repository.JefePrestamistaRepository;
import com.gestion.prestamo.repository.PrestarioRepository;
import com.gestion.prestamo.repository.UsuarioRepository;

@Service
public class PrestarioService {
    @Autowired
    private PrestarioRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Prestatario> lista() {
        return repository.findAll();
    }

    public Prestatario buscarPorId(int cod) {
        return repository.findById(cod).orElse(null);
    }
    
    public Prestatario buscarPorUsu(int cod) {
    	Prestatario p = new Prestatario();
    	
    	for (Prestatario pr : lista()) {
    		if (pr.getUsuario().getIdUsuario() == cod) {
    			p = pr;
    			break;
    		}
    	}
        return p;
    }

    public void guardar(Prestatario p) {
        repository.save(p);
    }

    public void eliminar(int cod) {
        repository.deleteById(cod);
    }

    @Transactional
    public void registrarPrestatario(Prestatario prestatario) {
        try {
            Usuario usuario = new Usuario();
            usuario = usuarioRepository.save(prestatario.getUsuario());
            prestatario.getUsuario().setIdUsuario(usuario.getIdUsuario());
            repository.save(prestatario);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

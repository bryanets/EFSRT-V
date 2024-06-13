package com.gestion.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.prestamo.entity.Grupo;
import com.gestion.prestamo.entity.JefePrestamista;
import com.gestion.prestamo.entity.Prestamista;
import com.gestion.prestamo.entity.Usuario;
import com.gestion.prestamo.repository.GrupoRepository;
import com.gestion.prestamo.repository.JefePrestamistaRepository;
import com.gestion.prestamo.repository.PrestamistaRepository;
import com.gestion.prestamo.repository.UsuarioRepository;

@Service
public class JefeService {
    @Autowired
    private PrestamistaRepository repository;

    @Autowired
    private UsuarioService usu;
    
    @Autowired
    private InversionistaServiceImpl inv;

    public List<Prestamista> lista() {
        return repository.findAll();
    }

    public Prestamista buscarPorId(int cod) {
        return repository.findById(cod).orElse(null);
    }
    
    public JefePrestamista buscarPorUsu(Usuario u) {
    	JefePrestamista p = new JefePrestamista();
    	for(JefePrestamista pe :inv.jefePrestamistas()) {
    		if (pe.getUsuario().getIdUsuario() == u.getIdUsuario()) {
    			p = pe;
    			break;
    		}
    	}
        return p;
    }

    public void guardar(Prestamista p) {
    	repository.save(p);
    }

    public void Eliminar(int cod) {
    	Prestamista p = new Prestamista();
    	p = buscarPorId(cod);
    	Usuario u = p.getUsuario();
        repository.deleteById(cod);
        usu.deleteById(u.getIdUsuario());
    }

}

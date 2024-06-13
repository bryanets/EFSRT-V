package com.gestion.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.prestamo.entity.Grupo;
import com.gestion.prestamo.entity.JefePrestamista;
import com.gestion.prestamo.entity.LocalComercial;
import com.gestion.prestamo.repository.GrupoRepository;
import com.gestion.prestamo.repository.LocalComercialRepository;

@Service
public class LocalComercialService {

    @Autowired
    private LocalComercialRepository repository;

    public List<LocalComercial> lista() {
        return repository.findAll();
    }

    public void guardar(LocalComercial l) {
        repository.save(l);
    }
    
    public LocalComercial buscarPorId(int cod) {
        return repository.findById(cod).orElse(null);
    }
}

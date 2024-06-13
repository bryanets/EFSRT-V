package com.gestion.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.prestamo.entity.Grupo;
import com.gestion.prestamo.repository.GrupoRepository;

@Service
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GrupoRepository repository;

    @Override
    public List<Grupo> grupos() {
        return repository.findAll();
    }

    @Override
    public void guardar(Grupo grupo) {
        repository.save(grupo);
    }

}

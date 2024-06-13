package com.gestion.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.prestamo.entity.Rol;
import com.gestion.prestamo.repository.RolRepository;

@Service
public class RolService {

	@Autowired
	private RolRepository repo;
	
	public void save(Rol r) {
		repo.save(r);
	}
	
	public void update(Rol r) {
		repo.save(r);
	}
	
	public void deleteById(Integer cod) {
		repo.deleteById(cod);
	}
	
	public Rol findById(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public List<Rol> findAll(){
		return repo.findAll();
	}
}

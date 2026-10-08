package com.isa.neri.sis.models.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.neri.sis.models.entity.Ciudad;
import com.isa.neri.sis.models.repository.CiudadRepository;

@Service
public class CiudadServiceImpl implements ICiudadService{

	@Autowired
	private CiudadRepository ciudadRepository;
		
	@Override
	public List<Ciudad> listaCiudades() {
		 
		return (List<Ciudad>) ciudadRepository.findAll();
	}

	@Transactional
	public void guardar(Ciudad ciudad) {
		ciudadRepository.save(ciudad);
	}

	@Transactional
	public Ciudad buscarPorId(Integer id) {
		return ciudadRepository.findById(id).orElse(null);
	}

	@Transactional
	public void eliminar(Integer id) {
		ciudadRepository.deleteById(id);
	}

}

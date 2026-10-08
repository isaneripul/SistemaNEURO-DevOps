package com.isa.neri.sis.models.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.neri.sis.models.entity.Estudios;
import com.isa.neri.sis.models.repository.EstudioRepository;

@Service
public class EstudioServiceImpl implements IEstudioService{
	
	@Autowired
	private EstudioRepository estudioRepository;

	@Override
	public List<Estudios> listarEstudios() {
		
		return (List<Estudios>) estudioRepository.findAll();
	}

	@Transactional
	public void guardar(Estudios estudios) {
		
		estudioRepository.save(estudios);
	}

	@Transactional
	public Estudios buscarPorId(Integer id) {
		
		return estudioRepository.findById(id).orElse(null);
	}

	@Transactional
	public void eliminar(Integer id) {
		
		estudioRepository.deleteById(id);
	}

}

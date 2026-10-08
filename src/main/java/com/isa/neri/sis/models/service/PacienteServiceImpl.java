package com.isa.neri.sis.models.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.neri.sis.models.entity.Pacientes;
import com.isa.neri.sis.models.repository.PacientesRepository;

@Service
public class PacienteServiceImpl implements IPacienteService{

	@Autowired
	private PacientesRepository pacienteRepository;
	
	@Override
	public List<Pacientes> listarPacientes() {
		
		return  (List<Pacientes>) pacienteRepository.findAll();
	}

	@Transactional
	public void guardar(Pacientes pacientes) {

		pacienteRepository.save(pacientes);
	}

	@Transactional
	public Pacientes buscarPorId(Integer id) {
	
		return pacienteRepository.findById(id).orElse(null);
	}

	@Transactional
	public void eliminar(Integer id) {
		pacienteRepository.deleteById(id);
		
	}

}

package com.isa.neri.sis.models.service;

import java.util.List;

import com.isa.neri.sis.models.entity.Pacientes;

public interface IPacienteService {

	public List<Pacientes> listarPacientes();
	public void guardar(Pacientes pacientes);
	public Pacientes buscarPorId(Integer id);
	public void eliminar(Integer id);
	
	
}

package com.isa.neri.sis.models.service;

import java.util.List;

import com.isa.neri.sis.models.entity.Estudios;

public interface IEstudioService {

	public List<Estudios> listarEstudios();
	public void guardar(Estudios estudios);
	public Estudios buscarPorId(Integer id);
	public void eliminar(Integer id);
	
	
}

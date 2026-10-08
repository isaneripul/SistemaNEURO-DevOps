package com.isa.neri.sis.models.service;

import java.util.List;

import com.isa.neri.sis.models.entity.Ciudad;
 

public interface ICiudadService {

	public List<Ciudad> listaCiudades();
	public void guardar(Ciudad cliente);
	public Ciudad buscarPorId(Integer id);
	public void eliminar(Integer id);

}

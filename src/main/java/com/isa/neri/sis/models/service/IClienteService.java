package com.isa.neri.sis.models.service;

import java.util.List;

import com.isa.neri.sis.models.entity.Cliente;

public interface IClienteService {
	public List<Cliente> listarTodos();
	public void guardar(Cliente cliente);
	public Cliente buscarPorId(Integer id);
	public void eliminar(Integer id);
	
}

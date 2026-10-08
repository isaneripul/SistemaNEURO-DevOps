package com.isa.neri.sis.models.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.neri.sis.models.entity.Cliente;
import com.isa.neri.sis.models.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> listarTodos() {
		
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Transactional
	public void guardar(Cliente cliente) {
		clienteRepository.save(cliente);

	}

	@Transactional
	public Cliente buscarPorId(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Transactional
	public void eliminar(Integer id) {
		clienteRepository.deleteById(id);

	}

}

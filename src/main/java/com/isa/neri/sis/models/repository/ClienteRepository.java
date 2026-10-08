package com.isa.neri.sis.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.isa.neri.sis.models.entity.Cliente;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Integer> {
	
	Page<Cliente> findByIdclienteContainingOrNombreContainingOrApaternoContainingOrAmaternoContainingOrTelefonoContainingOrEmailContainingAllIgnoreCase(
			String idcliente,
			String nombre,
			String apaterno,
			String amaterno,
			String telefono,
			String email,
			Pageable pageable);
	
}

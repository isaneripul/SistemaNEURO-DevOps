package com.isa.neri.sis.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.isa.neri.sis.models.entity.Pacientes;

@Repository									 
public interface PacientesRepository extends PagingAndSortingRepository<Pacientes, Integer>{
	
	Page<Pacientes> findAll(Pageable pageable);	
																																																	   
	//Page<Pacientes> findByIdpacienteContainingOrnombreContainingOrapaternoContainingOramaternoContainingOrsexoContainingOredadContainingOrfhnacimientoContainingOrfhentradaContainingOrfhaltaContainingAllIgnoreCase(
	//		String idpaciente,String nombre,String apaterno,String amaterno,String sexo,String edad,String fhnacimiento,String fhentrada,
	//		String fhalta,Pageable pageable);	
	
	
}

package com.isa.neri.sis.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.isa.neri.sis.models.entity.Ciudad;

@Repository
public interface CiudadRepository extends PagingAndSortingRepository<Ciudad, Integer> {
	
	Page<Ciudad> findByIdciudadContainingOrNomciudadContainingOrFechaContainingAllIgnoreCase(
			String idciudad,
			String nomciudad,
			String fecha,
			Pageable pageable);
	
}

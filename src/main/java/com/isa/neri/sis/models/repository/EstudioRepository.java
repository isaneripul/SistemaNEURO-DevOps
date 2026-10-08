package com.isa.neri.sis.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.isa.neri.sis.models.entity.Estudios;

@Repository
public interface EstudioRepository extends PagingAndSortingRepository<Estudios, Integer>{

	Page<Estudios> findByIdestudioContainingOrEstudioContainingAllIgnoreCase(
			String idestudio,
			String estudio,
			Pageable pageable);	
}

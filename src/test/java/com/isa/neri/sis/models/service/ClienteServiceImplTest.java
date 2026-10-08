package com.isa.neri.sis.models.service;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.isa.neri.sis.controller.ClienteController;
import com.isa.neri.sis.models.entity.Ciudad;
import com.isa.neri.sis.models.entity.Cliente;
import com.isa.neri.sis.models.repository.ClienteRepository;
import com.isa.neri.sis.models.service.*;
	  


@SpringBootTest
 class ClienteServiceImplTest {
	
	@Autowired
	private ClienteServiceImpl clienteServiceImpl;
	
	@MockBean
	private ClienteRepository clienteRepository;
	
	@Test
	void setUp() {
		Optional<Cliente> cliente = Optional.of(new Cliente());
		
		cliente.get().setIdcliente(1);
		cliente.get().setNombre("Isaias");
		cliente.get().setApaterno("Neri");
		cliente.get().setAmaterno("Pulido");
		cliente.get().setEmail("isaneripul@hotmail.com");
		cliente.get().setTelefono("5528506360");
		
		Mockito.when(clienteRepository.findById(1)).thenReturn(cliente);
		
	}

}

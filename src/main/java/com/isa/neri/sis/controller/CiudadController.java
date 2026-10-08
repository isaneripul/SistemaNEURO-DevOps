package com.isa.neri.sis.controller;

import java.util.List;

import javax.validation.Valid;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isa.neri.sis.models.entity.Ciudad;
import com.isa.neri.sis.models.repository.CiudadRepository;
import com.isa.neri.sis.models.service.ICiudadService;


@Controller
@RequestMapping("/views/ciudades")
public class CiudadController {

	@Autowired
	private CiudadRepository ciudadRepository;
	
	@Autowired
	private ICiudadService ciudadService;
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	private static final String ACTION_1 = "/views/ciudades/frmCiudad";
	
	@GetMapping("/")
	public String listarCiudades(@PageableDefault(size = 10) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model) {
		
		model.addAttribute("titulo", "Lista de Ciudades");
		if (value != null) {
			
			model.addAttribute("key", value);
			logger.info("Valor a buscar " + value);
			
			model.addAttribute("ciudades", ciudadRepository.findByIdciudadContainingOrNomciudadContainingOrFechaContainingAllIgnoreCase(value,value,value,pageable));
			return "/views/ciudades/listarCiudad";
		}
		else {			
			List<Ciudad> listadoCiudades = ciudadService.listaCiudades();
			model.addAttribute("ciudades", listadoCiudades);		
		}
		
		return "/views/ciudades/listarCiudad";
	}
	
	@GetMapping("/create")
	public String crear(Model model) {
		
		Ciudad ciudad = new Ciudad();
		model.addAttribute("titulo", "Formulario: Nueva Ciudad");	
		model.addAttribute("ciudad", ciudad);
		
		return ACTION_1;
	}	

	@PostMapping("/save")						        
	public String guardar(@Valid @ModelAttribute Ciudad ciudades, BindingResult result, Model model, RedirectAttributes attribute) {				
		logger.info("Ciuda: " + ciudades.getNomciudad());
		
		if(result.hasErrors()) {
			
			logger.info("Ciuda: " + ciudades.getNomciudad());
			model.addAttribute("titulo", "Formulario: Nueva Ciudad");
			model.addAttribute("ciuda", ciudades);
			logger.info("Error en el fomulario ! : ");
			
			return ACTION_1;
		}
		
		logger.info("Datos a insertar: " + ciudades.toString());
		ciudadService.guardar(ciudades);
		logger.info("Ciudad guardado con exito! ");
		attribute.addFlashAttribute("success", "Ciudad guardado con exito!");
		return "redirect:/views/ciudades/";				 
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id")Integer idCiudad, Model model,RedirectAttributes attribute) {
		logger.info("Dato a buscar " + idCiudad);
		Ciudad ciuda = null;
		if(idCiudad > 0) {
			ciuda = ciudadService.buscarPorId(idCiudad);
			if(ciuda == null) {
				logger.info("Error: el Id de la Ciudad no existe!");
				attribute.addFlashAttribute("error", "ATENCIÓN: el Id de la Ciudad no existe!");
				return "redirect:/views/ciudades/";
			}
		}else {
			logger.info("Error: Error con el Id de la Ciudad!");
			attribute.addFlashAttribute("error", "ATENCIÓN: Error con el Id de la Ciudad!");
			return "redirect:/views/ciudades/";
		}
		logger.info("Dato encontrado del campo de la tabla " + ciuda);
		logger.info("Datos encontrados " + ciuda.toString());
		model.addAttribute("titulo", "Formulario: Editar Ciudad");
		model.addAttribute("ciudad", ciuda);
		return ACTION_1;
	}
	
}

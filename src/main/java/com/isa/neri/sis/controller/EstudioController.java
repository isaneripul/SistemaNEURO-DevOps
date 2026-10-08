package com.isa.neri.sis.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

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
import com.isa.neri.sis.models.entity.Estudios;
import com.isa.neri.sis.models.repository.EstudioRepository;
import com.isa.neri.sis.models.service.IEstudioService;

@Controller
@RequestMapping("/views/estudios")
public class EstudioController {

	@Autowired
	private EstudioRepository estudioRepository;
	
	@Autowired
	private IEstudioService estudioService;
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	private static final String ACTION_1 = "/views/estudios/frmEstudios";
	
	@GetMapping("/")
	public String listarEstudios(@PageableDefault(size = 10) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model) {
		
		model.addAttribute("titulo", "Lista de Estudios de Laboratorio");
		if (value != null) {
			
			model.addAttribute("key", value);
			logger.info("Valor a buscar " + value);
			
			model.addAttribute("estudios", estudioRepository.findByIdestudioContainingOrEstudioContainingAllIgnoreCase(value,value,pageable));
			return "/views/estudios/listarEstudios";
		}
		else {			
			List<Estudios> listadoEstudios = estudioService.listarEstudios();
			model.addAttribute("estudios", listadoEstudios);		
		}
		
		return "/views/estudios/listarEstudios";
	}

	@GetMapping("/create")
	public String crear(Model model) {
		
		Estudios estudio = new Estudios();
		logger.info("Accede al proceso create ");
		model.addAttribute("titulo", "Formulario: Nuevo Estudio");	
		model.addAttribute("estudio", estudio);
		
		return ACTION_1;
	}		
	
	@PostMapping("/save")						        
	public String guardar(@Valid @ModelAttribute Estudios estudios, BindingResult result, Model model, RedirectAttributes attribute) {				
		logger.info("Estudio: " + estudios.getEstudio());
		
		if(result.hasErrors()) {
			
			logger.info("Estudio: " + estudios.getEstudio());
			model.addAttribute("titulo", "Formulario: Nuevo Estudio");
			model.addAttribute("estudio", estudios);
			logger.info("Error en el fomulario ! : ");
			
			return ACTION_1;
		}
		
		logger.info("Datos a insertar: " + estudios.toString());
		estudioService.guardar(estudios);
		logger.info("Estado guardado con exito! ");
		attribute.addFlashAttribute("success", "Estado guardado con exito!");
		return "redirect:/views/estudios/";				 
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id")Integer idEstudio, Model model,RedirectAttributes attribute) {
		logger.info("Dato a buscar " + idEstudio);
		Estudios estudio = null;
		if(idEstudio > 0) {
			estudio = estudioService.buscarPorId(idEstudio);
			if(estudio == null) {
				logger.info("Error: el Id del estado no existe!");
				attribute.addFlashAttribute("error", "ATENCIÓN: el Id del estado no existe!");
				return "redirect:/views/estudios/";
			}
		}else {
			logger.info("Error: Error con el Id del estudio!");
			attribute.addFlashAttribute("error", "ATENCIÓN: Error con el Id del estado!");
			return "redirect:/views/estudios/";
		}
		logger.info("Dato encontrado del campo de la tabla " + estudio);
		logger.info("Datos encontrados " + estudio.toString());
		model.addAttribute("titulo", "Formulario: Editar Estudio");
		model.addAttribute("estudio", estudio);
		return ACTION_1;
	}
	
	
}

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

import com.isa.neri.sis.models.entity.Pacientes;
import com.isa.neri.sis.models.repository.PacientesRepository;
import com.isa.neri.sis.models.service.IPacienteService;

@Controller
@RequestMapping("/views/pacientes")
public class PacienteController {

	@Autowired
	private IPacienteService pacienteService;
	
	@Autowired
	private PacientesRepository pacientesRepository;
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	private static final String ACTION_1 = "/views/pacientes/frmPacientes";	
	
	@GetMapping("/")
	public String listarPacientes(@PageableDefault(size = 10) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model) {
		
		logger.fine("Valor :" + value);
		logger.info("Pagina " + pageable);		
		model.addAttribute("titulo", "Lista de Pacientes");	
		
		if (value != null) {
			model.addAttribute("key ", value);			
			logger.info("Valor a buscar " + value);
			model.addAttribute("pacientes", 
					//pacientesRepository.findByIdpacienteContainingOrnombreContainingOrapaternoContainingOramaternoContainingOrsexoContainingOredadContainingOrfhnacimientoContainingOrfhentradaContainingOrfhaltaContainingAllIgnoreCase
					//(value, value, value, value, value, value, value, value, value, pageable));				
					pacientesRepository.findAll( pageable));							
			return "/views/pacientes/listarPacientes";
		}else {
			List<Pacientes> listadoPacientes = pacienteService.listarPacientes();
			model.addAttribute("pacientes", listadoPacientes);		
		}
		return "/views/pacientes/listarPacientes";
	}

	@GetMapping("/create")
	public String crear(Model model) {
		Pacientes paciente = new Pacientes();
		model.addAttribute("titulo", "Formulario: Nuevo Paciente");
		model.addAttribute("paciente", paciente);
		return ACTION_1;
	}
	
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Pacientes paciente, BindingResult result, Model model, RedirectAttributes attribute) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nuevo Paciente");
			model.addAttribute("paciente", paciente);
			logger.info("Existieron en el fomulario ! ");			
			return ACTION_1;
		}
		logger.info("Datos a insertar " + paciente.toString());
		pacienteService.guardar(paciente);
		logger.info("Paciente guardado con exito! ");
		attribute.addFlashAttribute("success", "Paciente guardado con exito!");		
		return "redirect:/views/pacientes/";
		
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id")Integer idPaciente, Model model,RedirectAttributes attribute) {
		Pacientes paciente = null;
		if(idPaciente > 0) {
			paciente = pacienteService.buscarPorId(idPaciente);
			if(paciente == null) {
				logger.info("Error: el Id del paciente no existe!");
				attribute.addFlashAttribute("error", "ATENCIÓN: el Id del paciente no existe!");
				return "redirect:/views/pacientes/";
			}
		}else {
			logger.info("Error: Error con el Id del paciente!");
			attribute.addFlashAttribute("error", "ATENCIÓN: Error con el Id del paciente!");
			return "redirect:/views/pacientes/";
		}
		
		model.addAttribute("titulo", "Formulario: Editar Paciente");
		model.addAttribute("paciente", paciente); 
		
		return "/views/pacientes/frmPacientes";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id")Integer idPaciente,RedirectAttributes attribute) {
		
		Pacientes paciente = null;
		
		if(idPaciente > 0) {
			paciente = pacienteService.buscarPorId(idPaciente);
			if(paciente == null) {
				logger.info("Error: el Id del paciente no existe! ");
				attribute.addFlashAttribute("error", "ATENCIÓN: el Id del paciente no existe!");
				return "redirect:/views/pacientes/";
			}
		}else {
			logger.info("Error: Error con el Id del paciente! ");
			attribute.addFlashAttribute("error", "ATENCIÓN: Error con el Id del paciente!");
			return "redirect:/views/pacientes/";
		}
		
		pacienteService.eliminar(idPaciente);
		logger.info("Registro eliminado con exito!");
		attribute.addFlashAttribute("warning", "Registro eliminado con exito!");
		return "redirect:/views/pacientes/";
	}	
	
}

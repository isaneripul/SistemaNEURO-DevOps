package com.isa.neri.sis.controller;

import java.util.List;
import java.util.logging.Logger;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.isa.neri.sis.models.entity.Ciudad;
import com.isa.neri.sis.models.entity.Cliente;
import com.isa.neri.sis.models.service.ICiudadService;
import com.isa.neri.sis.models.service.IClienteService;
import com.isa.neri.sis.models.repository.ClienteRepository;


@Controller
@RequestMapping("/views/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private ICiudadService ciudadService;
	
	@Autowired
	private ClienteRepository clienteRepository;	
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	@GetMapping("/")
	public String listarClientes(@PageableDefault(size = 10) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model) {
		
		logger.fine("Valor :" + value);
		logger.info("Pagina " + pageable);		
		
		model.addAttribute("titulo", "Lista de Clientes");	
		
		if (value != null) {
						
			model.addAttribute("key", value);
			
			logger.info("Valor a buscar " + value);
			
			model.addAttribute("clientes", clienteRepository.findByIdclienteContainingOrNombreContainingOrApaternoContainingOrAmaternoContainingOrTelefonoContainingOrEmailContainingAllIgnoreCase(value,
					value, value, value,value, value, pageable));
		
			return "/views/clientes/listar";
		}
		else {			
			List<Cliente> listadoClientes = clienteService.listarTodos();
			model.addAttribute("clientes", listadoClientes);		
		}		
		return "/views/clientes/listar";
	}
	
	@GetMapping("/create")
	public String crear(Model model) {
		
		Cliente cliente = new Cliente();
		List<Ciudad> listaCiudades = ciudadService.listaCiudades();
		
		model.addAttribute("titulo", "Formulario: Nuevo Cliente");
	
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listaCiudades);
		
		return "/views/clientes/frmCrear";
	}
	
	//public String guardar(Cliente cliente, Model model) {
	
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult result, Model model, RedirectAttributes attribute) {
		List<Ciudad> listaCiudades = ciudadService.listaCiudades();
		
		if(result.hasErrors()) {
			
			model.addAttribute("titulo", "Formulario: Nuevo Cliente");
			model.addAttribute("cliente", cliente);
			model.addAttribute("ciudades", listaCiudades);
			logger.info("Existieron en el fomulario ! ");			
			
			return "/views/clientes/frmCrear";
		}
		
		logger.info("Datos a insertar " + cliente.toString());
		clienteService.guardar(cliente);
		logger.info("Cliente guardado con exito! ");
		attribute.addFlashAttribute("success", "Cliente guardado con exito!");
		return "redirect:/views/clientes/";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id")Integer idCliente, Model model,RedirectAttributes attribute) {

		Cliente cliente = null;
		if(idCliente > 0) {
			cliente = clienteService.buscarPorId(idCliente);
			if(cliente == null) {
				logger.info("Error: el Id del Cliente no existe!");
				attribute.addFlashAttribute("error", "ATENCIÓN: el Id del Cliente no existe!");
				return "redirect:/views/clientes/";
			}
		}else {
			logger.info("Error: Error con el Id del Cliente!");
			attribute.addFlashAttribute("error", "ATENCIÓN: Error con el Id del Cliente!");
			return "redirect:/views/clientes/";
		}
		
		List<Ciudad> listaCiudades = ciudadService.listaCiudades();
		
		model.addAttribute("titulo", "Formulario: Editar Cliente");
		model.addAttribute("cliente", cliente); 
		model.addAttribute("ciudades", listaCiudades);
		
		return "/views/clientes/frmCrear";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id")Integer idCliente,RedirectAttributes attribute) {
		
		Cliente cliente = null;
		
		if(idCliente > 0) {
			cliente = clienteService.buscarPorId(idCliente);
			if(cliente == null) {
				logger.info("Error: el Id del Cliente no existe! ");
				attribute.addFlashAttribute("error", "ATENCIÓN: el Id del Cliente no existe!");
				return "redirect:/views/clientes/";
			}
		}else {
			logger.info("Error: Error con el Id del Cliente! ");
			attribute.addFlashAttribute("error", "ATENCIÓN: Error con el Id del Cliente!");
			return "redirect:/views/clientes/";
		}
		
		clienteService.eliminar(idCliente);
		logger.info("Registro eliminado con exito!");
		attribute.addFlashAttribute("warning", "Registro eliminado con exito!");
		return "redirect:/views/clientes/";
	}	
}

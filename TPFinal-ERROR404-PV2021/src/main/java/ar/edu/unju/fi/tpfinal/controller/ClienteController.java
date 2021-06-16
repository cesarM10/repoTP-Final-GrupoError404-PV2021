package ar.edu.unju.fi.tpfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import ar.edu.unju.fi.tpfinal.model.Customer;

@Controller
public class ClienteController {
//	@Autowired
//	@Qualifier("clienteUtilService")
//	private IClienteService clienteService;
	
//	@Autowired
//	@Qualifier("clienteObj")
//	private Customer cliente;
	
	@GetMapping("/cliente/nuevo")
	public String getNuevoClientePage(){//(Model model){
	//	model.addAttribute("cliente",cliente);

		return "alta_cliente";
	}
/*	
	
	@PostMapping("/cliente/guardar")
	public ModelAndView agregarClientePage(@ModelAttribute("cliente")Cliente cliente) {
		ModelAndView model = new ModelAndView("clientes");
		if (clienteService.obtenerClientes() == null) {
			clienteService.generarTablaCliente();
		}
		clienteService.agregarCliente(cliente);
		
		
		model.addObject("cliente", clienteService.obtenerClientes());
		return model;
	}
	
	@GetMapping("/cliente/listado")
	public ModelAndView getClientesPage() {
		ModelAndView model = new ModelAndView("clientes");
		if (clienteService.obtenerClientes() == null) {
			clienteService.generarTablaCliente();
		}
		
		model.addObject("cliente",clienteService.obtenerClientes());
		
		return model;
	}
	
*/	
}

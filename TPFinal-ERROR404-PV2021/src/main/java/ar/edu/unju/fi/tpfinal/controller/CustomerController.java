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
import ar.edu.unju.fi.tpfinal.service.ICustomerService;

@Controller
public class CustomerController {
	@Autowired
	@Qualifier("customerUtilService")
	private ICustomerService customerService;
	
	@Autowired
	@Qualifier("customerObject")
	private Customer customer;
	
	@GetMapping("/cliente/nuevo")
	public String getNuevoClientePage(Model model){//(){
		model.addAttribute("customer",customer);

		return "alta_customer";
	}
	
	
	@PostMapping("/cliente/guardar")
	public ModelAndView agregarCustomerPage(@ModelAttribute("customer")Customer customer) {
		ModelAndView model = new ModelAndView("alta_customer");
		if (customerService.obtenerCustomer() == null) {
			customerService.generarTablaCustomer();
		}
		customerService.agregarCustomer(customer);
		
		
		model.addObject("customer", customerService.obtenerCustomer());
		return model;
	}
	
	@GetMapping("/cliente/listado")
	public ModelAndView getCustomerPage() {
		ModelAndView model = new ModelAndView("alta_customer");
		if (customerService.obtenerCustomer() == null) {
			customerService.generarTablaCustomer();
		}
		
		model.addObject("customer",customerService.obtenerCustomer());
		
		return model;
	}
	

}

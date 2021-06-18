package ar.edu.unju.fi.tpfinal.controller;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tpfinal.model.Customer;
import ar.edu.unju.fi.tpfinal.service.ICustomerService;
import ar.edu.unju.fi.tpfinal.service.imp.CustomerServiceImp;

@Controller
public class CustomerController {
	
	
	private static final Log LOGGER = LogFactory.getLog(CustomerServiceImp.class);	
	
	
	
	@Autowired
	@Qualifier("customerServiceMysql")
	private ICustomerService customerService;
	/*
	@Autowired
	@Qualifier("customerUtilService")
	private ICustomerService customerService;
	
	@Autowired
	@Qualifier("customerObject")
	private Customer customer;
	
	
	
	@GetMapping("/cliente/nuevo")
	public String getNuevoCustomerPage(Model model){//(){
		model.addAttribute("customer",customer);

		return "alta_customer";
	}
	*/
	
	@GetMapping("/cliente/nuevo")
	public String getNuevoCustomerPage(Model model){//(){
		model.addAttribute("customer",customerService.getCustomer());

		return "alta_customer";
	}
	
	@PostMapping("/cliente/guardar")
	public ModelAndView agregarCustomerPage(@ModelAttribute("customer")Customer customer) {
		ModelAndView model = new ModelAndView("lista_customer");
/*	if (customerService.obtenerCustomer() == null) { //isEmpty()
			customerService.generarTablaCustomer();
		}
*/		
		customerService.agregarCustomer(customer);
		
		
		model.addObject("customer", customerService.obtenerCustomer());
		return model;
	}
	
	@GetMapping("/cliente/listado")
	public ModelAndView getCustomerPage() {
		ModelAndView model = new ModelAndView("lista_customer");
/*	if (customerService.obtenerCustomer() == null) { //isEmpty()
			customerService.generarTablaCustomer();
		}
*/		
		model.addObject("customer",customerService.obtenerCustomer());	
		return model;
	}
	
	//eliminar y modificar
	
	@GetMapping("/cliente/editar/{customerNumber}")
	public ModelAndView getCustomerEditPage(@PathVariable(value = "customerNumber")Long customerNumber) {
		LOGGER.info("METODO - - EDITAR CUSTOMER");
		ModelAndView model = new ModelAndView("alta_customer");
		Optional <Customer> customer = customerService.getCustomerPorId(customerNumber);
		
		model.addObject("customer", customer);
		return model;
	}
	
	@GetMapping("/cliente/eliminar/{customerNumber}")
	public ModelAndView getCustomerDeletePage(@PathVariable(value = "customerNumber")Long customerNumber) {
		ModelAndView model = new ModelAndView("redirect:/cliente/listado");
		customerService.eliminarCustomer(customerNumber);
		
		return model;
	}
	 
	 
	 

}

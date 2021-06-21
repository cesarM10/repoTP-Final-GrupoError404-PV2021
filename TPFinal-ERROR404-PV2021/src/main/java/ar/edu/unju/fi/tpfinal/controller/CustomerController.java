package ar.edu.unju.fi.tpfinal.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tpfinal.model.Customer;
import ar.edu.unju.fi.tpfinal.service.ICustomerService;
import ar.edu.unju.fi.tpfinal.service.IEmployeeService;
import ar.edu.unju.fi.tpfinal.service.imp.CustomerServiceImp;

@Controller
public class CustomerController {
	
	
	private static final Log LOGGER = LogFactory.getLog(CustomerServiceImp.class);	
	
	
	
	@Autowired
	@Qualifier("customerServiceMysql")
	private ICustomerService customerService;
	
	
	@Autowired
	@Qualifier("employeeServiceMysql")
	private IEmployeeService employeeService;

	
	@GetMapping("/cliente/nuevo")
	public String getNuevoCustomerPage(Model model){//(){
		model.addAttribute("customer",customerService.getCustomer());

		model.addAttribute("employees",employeeService.obtenerEmployees());//retorna el numero de la lista de employee
		return "alta_customer";
	}
	
	@PostMapping("/cliente/guardar")
 
	public ModelAndView agregarCustomerPage(@Valid @ModelAttribute("customer")Customer customer, BindingResult resultadoValidacion) {
	
		LOGGER.info("Metodo: guardando... --");
		ModelAndView model;
		
		if (resultadoValidacion.hasErrors()) { //En la validacion Si Encontro errores
			model = new ModelAndView("alta_customer");
			
		
			
			return  model;
		}else {//En la validacion no encontro errores
			model = new ModelAndView("lista_customer");	
			customerService.agregarCustomer(customer);	
			model.addObject("alta_customer", customerService.obtenerCustomer());
				return model;
		}
	
	}
	
	@GetMapping("/cliente/listado")
	public ModelAndView getCustomerPage() {
		ModelAndView model = new ModelAndView("lista_customer");		
		model.addObject("customer",customerService.obtenerCustomer());	
		return model;
	}
	
	//ELIMINAR Y MODIFICAR
	
	@GetMapping("/cliente/editar/{customerNumber}") //customerNumber es nuestro identicador que es de tipo Long
	public ModelAndView getCustomerEditPage(@PathVariable(value = "customerNumber")Long customerNumber) {
		LOGGER.info("METODO - - EDITAR CUSTOMER");
		ModelAndView model = new ModelAndView("alta_customer");
		Optional <Customer> customer = customerService.getCustomerPorId(customerNumber);
		model.addObject("customer", customer);
		model.addObject("employees", employeeService.obtenerEmployees());
		return model;
	}
	
	@GetMapping("/cliente/eliminar/{customerNumber}")
	public ModelAndView getCustomerDeletePage(@PathVariable(value = "customerNumber")Long customerNumber) {
		ModelAndView model = new ModelAndView("redirect:/cliente/listado"); //una vez eliminado volveremos a la lista 
		customerService.eliminarCustomer(customerNumber);
		model.addObject("customer",customerService.obtenerCustomer());
		return model;
	}
	 
	 
	 

}


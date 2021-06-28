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

@Controller //anotacion especial Controller
public class CustomerController {
	
	
	private static final Log LOGGER = LogFactory.getLog(CustomerServiceImp.class);	
	
	
	
	@Autowired
	@Qualifier("customerServiceMysql")
	private ICustomerService customerService; 
	
	
	@Autowired
	@Qualifier("employeeServiceMysql")
	private IEmployeeService employeeService;//inyeccion de la capa de Service Employee.

	/**
	 * @return Nos mostara el Fomulario para hacer la Alta de Customer.
	 */
	@GetMapping("/cliente/nuevo")
	public String getNuevoCustomerPage(Model model){ 
		model.addAttribute("customer",customerService.getCustomer());//inyeccion(envio) del objeto customer. 

		model.addAttribute("employees",employeeService.obtenerEmployees());//retorna  la lista de employee.
		return "alta_customer"; 
	}
	/**
	 * @return Guardara al Customer.
	 */
	@PostMapping("/cliente/guardar")
	public ModelAndView agregarCustomerPage(@Valid @ModelAttribute("customer")Customer customer, BindingResult resultadoValidacion) {
		LOGGER.info("Metodo: guardando... --");
		ModelAndView model;
		
		if (resultadoValidacion.hasErrors()) { //En la validacion Si Encontro errores
			model = new ModelAndView("alta_customer");
			model.addObject("employees",employeeService.obtenerEmployees());
			return  model;
		}else {//En la validacion no encontro errores
			model = new ModelAndView("lista_customer");	
			customerService.agregarCustomer(customer);
			model.addObject("customer", customerService.obtenerCustomer());
				return model;
		}
	
	}
	
	/**
	 * 
	 * @return Mostrara una lista ya sea que este vacia o no. 
	 */
	@GetMapping("/cliente/listado")
	public ModelAndView getCustomerPage() {
		ModelAndView model = new ModelAndView("lista_customer");		
		model.addObject("customer",customerService.obtenerCustomer());
		return model;
	}
	
	/**
	 * @return Edita al Customer
	 */
	
	@GetMapping("/cliente/editar/{customerNumber}") 
	public ModelAndView getCustomerEditPage(@PathVariable(value = "customerNumber")Long customerNumber) { 
		LOGGER.info("METODO - - EDITAR CUSTOMER");
		ModelAndView model = new ModelAndView("alta_customer");
		Optional <Customer> customer = customerService.getCustomerPorId(customerNumber);
		model.addObject("customer", customer);//enviara al formulario alta customer.
		model.addObject("employees", employeeService.obtenerEmployees());
		return model; 
	}
	
	
	/**
	 * @return  Elimina al Customer.
	 */
	
	@GetMapping("/cliente/eliminar/{customerNumber}")
	public ModelAndView getCustomerDeletePage(@PathVariable(value = "customerNumber")Long customerNumber) { //valor y el tipo.
		ModelAndView model = new ModelAndView("redirect:/cliente/listado"); //una vez eliminado volveremos a la lista .
		customerService.eliminarCustomer(customerNumber);//envia el parametro para borrar.
		model.addObject("customer",customerService.obtenerCustomer());
		return model;  
	}
	 
	 
	 

}


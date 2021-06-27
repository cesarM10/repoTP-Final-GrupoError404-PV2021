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
	@Qualifier("customerServiceMysql")//implementador para comunicar con la BD.
	private ICustomerService customerService; //inyeccion de la capa de Service Customer.
	
	
	@Autowired
	@Qualifier("employeeServiceMysql")//implementador para comunicar con la BD.
	private IEmployeeService employeeService;//inyeccion de la capa de Service Employee.

	
	@GetMapping("/cliente/nuevo")
	public String getNuevoCustomerPage(Model model){ //nombre del metodo getNuevoCustomerPage
		model.addAttribute("customer",customerService.getCustomer());//inyeccion(envio) del objeto customer. 

		model.addAttribute("employees",employeeService.obtenerEmployees());//retorna  la lista de employee.
		return "alta_customer"; //nombre de la pagina html.
	}
	
	@PostMapping("/cliente/guardar")//petición PostMapping.
	                                      //objeto del tipo Customer
	public ModelAndView agregarCustomerPage(@Valid @ModelAttribute("customer")Customer customer, BindingResult resultadoValidacion) {
		LOGGER.info("Metodo: guardando... --");
		ModelAndView model;
		
		if (resultadoValidacion.hasErrors()) { //En la validacion Si Encontro errores

			model = new ModelAndView("alta_customer");
			//model.addObject("customer",customerService.getCustomer());

			model.addObject("employees",employeeService.obtenerEmployees());
		//	model.addObject("lista_employee", employeeService.obtenerEmployees());//datos de employees
			
			return  model;
		}else {//En la validacion no encontro errores
			model = new ModelAndView("lista_customer");
			
			
	//		Employee employee = employeeService.updateNuemeroDeEmpleado(customer.getCustomerNumber());
	//		customer.setSalesRepEmployeeNumber(employee);
			
	//		LOGGER.info("PASA O NO PASA --"+employee);
			
			customerService.agregarCustomer(customer);
		//	model.addObject("customer", customerService.getCustomer());//
		//	model.addObject("customer", customerService.obtenerCustomer());
			model.addObject("customer", customerService.obtenerCustomer());
			
	//		LOGGER.info(employeeService.obtenerEmployees());

				return model;
		}
	
	}
	
	//LISTADO
	@GetMapping("/cliente/listado")
	public ModelAndView getCustomerPage() {
		ModelAndView model = new ModelAndView("lista_customer");		
		model.addObject("customer",customerService.obtenerCustomer());	//podremos ver la lista ya sea que este cargada o no con los datos.
		return model;
	}
	
	//ELIMINAR Y MODIFICAR
	
	@GetMapping("/cliente/editar/{customerNumber}") //variable customerNumber .
	public ModelAndView getCustomerEditPage(@PathVariable(value = "customerNumber")Long customerNumber) { //el valor de la variable y su tipo.
		LOGGER.info("METODO - - EDITAR CUSTOMER");
		ModelAndView model = new ModelAndView("alta_customer");//html al cual modificar.
		Optional <Customer> customer = customerService.getCustomerPorId(customerNumber);//recupera Al customer por su id en este caso "customerNumber".
		model.addObject("customer", customer);//enviara al formulario alta customer.
		model.addObject("employees", employeeService.obtenerEmployees());
		return model; //retorna el model.
	}
	
	@GetMapping("/cliente/eliminar/{customerNumber}")
	public ModelAndView getCustomerDeletePage(@PathVariable(value = "customerNumber")Long customerNumber) { //valor y el tipo.
		ModelAndView model = new ModelAndView("redirect:/cliente/listado"); //una vez eliminado volveremos a la lista .
		customerService.eliminarCustomer(customerNumber);//envia el parametro para borrar.
		model.addObject("customer",customerService.obtenerCustomer());
		return model;  //retorno del model.
	}
	 
	 
	 

}


package ar.edu.unju.fi.tpfinal.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tpfinal.model.Employee;

import ar.edu.unju.fi.tpfinal.service.IEmployeeService;
import ar.edu.unju.fi.tpfinal.service.IOfficeService;
import ar.edu.unju.fi.tpfinal.service.imp.EmployeeServiceMysql;

@Controller
public class EmployeeController {
	private static final Log LOGGER = LogFactory.getLog(EmployeeServiceMysql.class);
	
	@Autowired
	@Qualifier("employeeServiceMysql")
	private IEmployeeService employeeService;
	
	@Autowired
	private Employee employee;
	
	@Autowired
	@Qualifier("officeServiceMysql")
	private IOfficeService officeService;
	
	@GetMapping("/employee/nuevo")
	public String getNuevoEmployeePage(Model model) {
		model.addAttribute("employee", employee);
		model.addAttribute("employeeB", employeeService.getEmployee());
		model.addAttribute("offices", officeService.obtenerOffices());
		model.addAttribute("employeeSeleccionado", employeeService.listaEmployeeSeleccionado());
		return "alta-employee";
	}
	
	@PostMapping("/employee/guardar")
	public ModelAndView agregarEmployeePage(@ModelAttribute("employee")Employee employee) {
		LOGGER.info("ENTRO A GUARDAR");
		ModelAndView modelv = new ModelAndView("lista-employee");
		
		//if(employeeService.listaEmployeeSeleccionado().isEmpty()) {
			//employee.setReportsTo(employeeService.getEmployee());
		//}else {
		//RESOLVER QUE NO CAPTURA EL officeCode EN employee.
		LOGGER.info("AQUI AQUI AQUI AQUI AQUI AQUI AQUI" + employee);
		employee.setOffice(officeService.getOfficePorCodigo(1L));	
		employee.setReportsTo(employeeService.listaEmployeeSeleccionado().get(0));
			
		//}
		LOGGER.info("PASO EL IF");
		//Employee employee1 = new Employee();
		
		//employee1 = employee;
		//LOGGER.info("LO QUE VA A GUARDAR" + employee1);
		/*for (Office office : officeService.obtenerOffices()) {
			if(office.getOfficeCode() == employee.getOffice().getOfficeCode()) {
				employee.setOffice(office);
			}
		}*/
		
		
		
		//employee.setOffice(officeService.obtenerOffices().get(0));
		employeeService.agregarEmployee(employee);
		LOGGER.info("PASO EL GUARDAR");
		modelv.addObject("employees", employeeService.obtenerEmployees());
		return modelv;
	}
	
	@GetMapping("/employee/listado")
	public ModelAndView getEmployeesPage() {
		ModelAndView model = new ModelAndView("lista-employee");
		
		model.addObject("employees", employeeService.obtenerEmployees());
		return model;
	}
	
	@GetMapping("/employee/busqueda")
	public String getBuscarEmployeeConFiltro(@RequestParam(value = "employeeNumber")Long employeeNumber, Model model) {
		LOGGER.info("METODO - - BUSCAR");
		model.addAttribute("employee", employee); 
		model.addAttribute("employeeB", employeeService.getEmployee());
		model.addAttribute("employeeSeleccionado", employeeService.buscarEmployeePorEmployeeNumber(employeeNumber));
		LOGGER.info("METODO - - BUSCAR - - PASO");
		
		model.addAttribute("offices", officeService.obtenerOffices());
		return "alta-employee";
	}
	
	@GetMapping("/employee/quitaropcion/{employeeNumber}")
	public ModelAndView quitarOpcionListaEmployeeSeleccionado(@PathVariable(name = "employeeNumber")Long employeeNumber) {
		ModelAndView model = new ModelAndView("alta-employee");
		employeeService.quitarEmployeeListaSeleccionado(employeeNumber);
		model.addObject("employee", employee);
		model.addObject("employeeB", employeeService.getEmployee());
		model.addObject("employeeSeleccionado", employeeService.listaEmployeeSeleccionado());
		
		model.addObject("offices", officeService.obtenerOffices());
		
		
		return model;
	}
	
	@GetMapping("/employee/editar/{employeeNumber}")
	public ModelAndView getCustomerEditPage(@PathVariable(value = "employeeNumber")Long employeeNumber) {
		LOGGER.info("METODO - - EDITAR EMPLOYEE");
		ModelAndView model = new ModelAndView("alta-employee");
		
		Optional<Employee> employee = employeeService.getEmployeePorEmployeeNumber(employeeNumber);
		
		List<Employee> employeeSeleccionado = employeeService.buscarEmployeePorEmployeeNumber(employee.get().getReportsTo().getEmployeeNumber());
		
		model.addObject("employee", employee);
		
		model.addObject("employeeB", employeeService.getEmployee());
		model.addObject("offices", officeService.obtenerOffices());
		
		
		model.addObject("employeeSeleccionado", employeeSeleccionado);
		return model;
	}
	
	@GetMapping("/employee/eliminar/{employeeNumber}")
	public ModelAndView getCustomerDeletePage(@PathVariable(value = "employeeNumber")Long employeeNumber) {
		ModelAndView model = new ModelAndView("redirect:/employee/listado");
		
		employeeService.eliminarEmployee(employeeNumber);
		
		model.addObject("employees", employeeService.obtenerEmployees());
		return model;
	}
}

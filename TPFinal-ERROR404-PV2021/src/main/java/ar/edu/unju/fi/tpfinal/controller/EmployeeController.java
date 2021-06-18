package ar.edu.unju.fi.tpfinal.controller;

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
import ar.edu.unju.fi.tpfinal.model.Office;
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
	@Qualifier("officeServiceMysql")
	private IOfficeService officeService;
	
	@GetMapping("/employee/nuevo")
	public String getNuevoEmployeePage(Model model) {
		model.addAttribute("employee", employeeService.getEmployee());
		model.addAttribute("offices", officeService.obtenerOffices());
		model.addAttribute("employeeSelecionado", employeeService.listaEmployeeSeleccionado());
		return "alta-employee";
	}
	
	@PostMapping("/employee/guardar")
	public ModelAndView agregarEmployeePage(@ModelAttribute("employee")Employee employee) {
		ModelAndView modelv = new ModelAndView("alta-employee");
		
		if(employeeService.listaEmployeeSeleccionado().isEmpty()) {
			employee.setReportsTo(employeeService.getEmployee());
		}else {
			
			employee.setReportsTo(employeeService.listaEmployeeSeleccionado().get(employeeService.listaEmployeeSeleccionado().size()-1));
			
		}
		Employee employee1 = new Employee();
		employee1 = employee;
		for (Office office : officeService.obtenerOffices()) {
			if(office.getOfficeCode() == employee.getOffice().getOfficeCode()) {
				employee1.setOffice(office);
			}
		}
		employeeService.agregarEmployee(employee1);
		
		modelv.addObject("employees", employeeService.obtenerEmployees());
		return modelv;
	}
	
	@GetMapping("/employee/busqueda")
	public String getBuscarEmployeeConFiltro(@RequestParam(value = "employeeNumber")Long employeeNumber, Model model) {
		LOGGER.info("METODO - - BUSCAR");
		model.addAttribute("employee", employeeService.getEmployee()); 
		model.addAttribute("employeeSeleccionado", employeeService.buscarEmployeePorEmployeeNumber(employeeNumber));
		LOGGER.info("METODO - - BUSCAR - - PASO");
		
		model.addAttribute("offices", officeService.obtenerOffices());
		return "alta-employee";
	}
	
	@GetMapping("/employee/quitaropcion/{employeeNumber}")
	public ModelAndView quitarOpcionListaEmployeeSeleccionado(@PathVariable(name = "employeeNumber")Long employeeNumber) {
		ModelAndView model = new ModelAndView("alta-employee");
		employeeService.quitarEmployeeListaSeleccionado(employeeNumber);
		model.addObject("employee", employeeService.getEmployee());
		
		model.addObject("employeeSeleccionado", employeeService.listaEmployeeSeleccionado());
		
		model.addObject("offices", officeService.obtenerOffices());
		
		
		return model;
	}
}

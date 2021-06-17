package ar.edu.unju.fi.tpfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.fi.tpfinal.service.IEmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	@Qualifier("employeeServiceMysql")
	private IEmployeeService employeeService;
	
	@GetMapping("/employee/nuevo")
	public String getNuevoEmployeePage(Model model) {
		model.addAttribute("employee", employeeService.getEmployee());
		//model.addAttributes("offices", officeService.obtenerOffices());
		//model.addAttribute("employeeSelecionado", employeeService.listEmployeeSelecionado());
		return "alta-employee";
	}
}

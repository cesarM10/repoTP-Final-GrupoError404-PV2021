package ar.edu.unju.fi.tpfinal.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tpfinal.model.Employee;

import ar.edu.unju.fi.tpfinal.service.IEmployeeService;
import ar.edu.unju.fi.tpfinal.service.IOfficeService;
import ar.edu.unju.fi.tpfinal.service.imp.EmployeeServiceMysql;
/**
 * @author 
 * Integrantes del Grupo Error404:
	 * Gaspar, Alvaro Emanuel
	 * Mercado, Cesar David
	 * Rodriguez, Enrique Sebastian
	 * Salas, Ivan Arnaldo
 */
@Controller
public class EmployeeController {
	private static final Log LOGGER = LogFactory.getLog(EmployeeServiceMysql.class);
	
	@Autowired
	@Qualifier("employeeServiceMysql")
	private IEmployeeService employeeService; //inyeccion EmployeeService con metodos que trabaja con la base de datos.
	
	@Autowired
	private Employee employee; //inyeccion objeto employee
	
	@Autowired
	@Qualifier("officeServiceMysql")
	private IOfficeService officeService; //inyeccion OfficeService con metodos que trabaja con la base de datos
	
	/**
	 * Metodo que agrega un objeto employee, lista de oficinas, lista de un empleado selecionado
	 *  necesario para el alta de un Empleado.
	 * 
	 * @param model
	 * @return dirige al alta-employee.html
	 */
	@GetMapping("/employee/nuevo")
	public String getNuevoEmployeePage(Model model) {
		model.addAttribute("employee", employeeService.getEmployee());
		//model.addAttribute("employeeB", employeeService.getEmployee());
		model.addAttribute("offices", officeService.obtenerOffices());
		model.addAttribute("employeeSeleccionado", employeeService.listaEmployeeSeleccionado());
		return "alta-employee";
	}
	
	/**
	 * Metodo que recibe un employee con los datos cargados en el html de alta
	 * y un BindingResult que trae el resultado de las validaciones correspondientes a cada campo 
	 * que se cargo. Comprueba que no haya errores en la carga de los campos, si hay errores, volvera
	 * al html de alta y mostrara los campos en los que se ingreso datos erroneos, sino se procede a 
	 * agregar el employee a la base de datos.
	 * @param employee
	 * @param resultadoValidacion
	 * @return 
	 */
	@PostMapping("/employee/guardar")
	public ModelAndView agregarEmployeePage(@Valid @ModelAttribute("employee")Employee employee, BindingResult resultadoValidacion) {
		LOGGER.info("ENTRO A GUARDAR");
		//ModelAndView model = new ModelAndView("lista-employee");
		ModelAndView model;
		
		if(resultadoValidacion.hasErrors()) {
			model = new ModelAndView("alta-employee");
			
			//model.addObject("employee", employeeService.getEmployee());
			model.addObject("offices", officeService.obtenerOffices());
			model.addObject("employeeSeleccionado", employeeService.listaEmployeeSeleccionado());
			
			return model;
		}else {
			model = new ModelAndView("lista-employee");
			
			LOGGER.info("AQUI AQUI AQUI AQUI AQUI" + employee);
			//Optional<Office> officeOp= officeService.getOfficePorCodigo(1L);
			//employee.setOffice(officeOp.get());	
			employee.setReportsTo(employeeService.listaEmployeeSeleccionado().get(0));
				
			LOGGER.info("PASO EL IF");
			
			employeeService.agregarEmployee(employee);
			LOGGER.info("PASO EL GUARDAR");
			model.addObject("employees", employeeService.obtenerEmployees());
			return model;
		}
		
	}
	
	/**
	 * Metodo que nos agrega a la vista de lista-employee una lista cargada con los
	 * empleados que tenemos en la base de datos
	 * @return 
	 */
	@GetMapping("/employee/listado")
	public ModelAndView getEmployeesPage() {
		ModelAndView model = new ModelAndView("lista-employee");
		
		model.addObject("employees", employeeService.obtenerEmployees());
		return model;
	}
	
	/**
	 * Metodo que que recibe como parametro employeeNumber para realizar la busqueda de
	 * un empleado encargado del nuevo empleado.
	 * @param employeeNumber
	 * @param model
	 * @return  Nos devuelve al html de alta-employee
	 */
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
	
	/**
	 * Metodo que quita el empleado seleccionado como encargado en el metodo getBuscarEmployeeConFiltro.
	 * 
	 * @param employeeNumber
	 * @return Retornamos al hmtl de alta-employee.
	 */
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
	
	/**
	 * Metodo que nos permite editar los datos de un empleado. Mediante el employeeNumber permite la
	 * busqueda en la base de datos para traer los datos a los campos en alta-employe.
	 * @param employeeNumber
	 * @return alta-employee con los datos del empleado a editar
	 */
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
	
	/**
	 * Metodo que mediante employeeNumber permite eliminar el empleado de la base de datos que coincida.
	 * 
	 * @param employeeNumber
	 * @return Redirige a la direccion /employee/listado.
	 */
	@GetMapping("/employee/eliminar/{employeeNumber}")
	public ModelAndView getCustomerDeletePage(@PathVariable(value = "employeeNumber")Long employeeNumber) {
		ModelAndView model = new ModelAndView("redirect:/employee/listado");
		
		employeeService.eliminarEmployee(employeeNumber);
		
		model.addObject("employees", employeeService.obtenerEmployees());
		return model;
	}
}

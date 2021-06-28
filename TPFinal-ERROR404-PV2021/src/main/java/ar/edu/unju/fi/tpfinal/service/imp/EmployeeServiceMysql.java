package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Employee;
import ar.edu.unju.fi.tpfinal.repository.IEmployeeRepository;
import ar.edu.unju.fi.tpfinal.service.IEmployeeService;

@Service("employeeServiceMysql")
public class EmployeeServiceMysql implements IEmployeeService{
	private static final Log LOGGER = LogFactory.getLog(EmployeeServiceMysql.class);
	
	List<Employee> employeeSeleccionado = new ArrayList<Employee>(); //nueva lista
	
	@Autowired
	private Employee employee = new Employee();
	
	@Autowired
	private IEmployeeRepository employeeRepository; //inyecion de metodos que implementa la base de datos.
	
	
	/**
	 * Agrega un objeto del tipo Employee a la base de datos.
	 */
	@Override
	public void agregarEmployee(Employee employee) {
		employeeRepository.save(employee);
		
	}

	/**
	 * Crea un nuevo objeto del tipo Employee
	 */
	@Override
	public Employee getEmployee() {
		
		return employee;
	}
	
	/**
	 * Obtiene todos los empleados de la base de datos en una lista.
	 */
	@Override
	public List<Employee> obtenerEmployees() {
		List<Employee> employees = (List<Employee>) employeeRepository.findAll();
		return employees;
	}

	/**
	 * Elimina un empleado de la base de dato por employeeNumber
	 */
	@Override
	public void eliminarEmployee(Long employeeNumber) {
		employeeRepository.deleteById(employeeNumber);
	}

	/**
	 * Devuelve una lista con un empleado seleccionado.
	 */
	@Override
	public List<Employee> listaEmployeeSeleccionado() {
		
		return employeeSeleccionado;
	}

	/**
	 * Busca una empleado por employeeNumber y devuelve la lista cargada.
	 */
	@Override
	public List<Employee> buscarEmployeePorEmployeeNumber(Long employeeNumber) {
		if(employeeNumber == null) {
			employeeSeleccionado.add(employee);
		}else {
			employeeSeleccionado = employeeRepository.findByEmployeeNumber(employeeNumber);
			LOGGER.info("ENTRO POR VALOR INGRESADO");
		}
		
		return employeeSeleccionado;
	}

	/**
	 * Quita un empleado de la lista employeeSeleccionado.
	 */
	@Override
	public void quitarEmployeeListaSeleccionado(Long employeeNumber) {
		for (int i = 0; i < employeeSeleccionado.size(); i++) {
			if(employeeSeleccionado.get(i).getEmployeeNumber() == employeeNumber){
				employeeSeleccionado.remove(i);
			}
		}
	}

	/**
	 * Retorna un objeto Employee buscado en la base de datos por employeeNumber.
	 * Si no lo encuentra devuelve un objeto vacio.
	 */
	@Override
	public Optional<Employee> getEmployeePorEmployeeNumber(Long employeeNumber) {
		Optional<Employee> employee = employeeRepository.findById(employeeNumber);
		return employee;
	}

}


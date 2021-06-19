package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.ArrayList;
import java.util.List;

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
	
	List<Employee> employeeSeleccionado = new ArrayList<Employee>();
	
	@Autowired
	private Employee employee = new Employee();
	
	@Autowired
	private IEmployeeRepository employeeRepository;
	
	@Override
	public void agregarEmployee(Employee employee) {
		employeeRepository.save(employee);
		
	}

	@Override
	public Employee getEmployee() {
		
		return employee;
	}
	
	@Override
	public List<Employee> obtenerEmployees() {
		List<Employee> employees = (List<Employee>) employeeRepository.findAll();
		return employees;
	}

	@Override
	public void eliminarEmployee(Long empoyeeNumber) {
		employeeRepository.deleteByEmployeeNumber(empoyeeNumber);
		
	}

	@Override
	public List<Employee> listaEmployeeSeleccionado() {
		
		return employeeSeleccionado;
	}

	@Override
	public List<Employee> buscarEmployeePorEmployeeNumber(Long employeeNumber) {
		if(employeeNumber == null) {
			
		}else {
			employeeSeleccionado = employeeRepository.findByEmployeeNumber(employeeNumber);
			LOGGER.info("ENTRO POR VALOR INGRESADO");
		}
		
		return employeeSeleccionado;
	}

	@Override
	public void quitarEmployeeListaSeleccionado(Long employeeNumber) {
		for (int i = 0; i < employeeSeleccionado.size(); i++) {
			if(employeeSeleccionado.get(i).getEmployeeNumber() == employeeNumber){
				employeeSeleccionado.remove(i);
			}
		}
		
	}

	

}


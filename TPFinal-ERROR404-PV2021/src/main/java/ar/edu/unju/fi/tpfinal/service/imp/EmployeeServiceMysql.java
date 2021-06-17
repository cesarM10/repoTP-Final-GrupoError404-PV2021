package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Employee;
import ar.edu.unju.fi.tpfinal.repository.IEmployeeRepository;
import ar.edu.unju.fi.tpfinal.service.IEmployeeService;

@Service("employeeServiceMysql")
public class EmployeeServiceMysql implements IEmployeeService{

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

	

}

package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;


import ar.edu.unju.fi.tpfinal.model.Employee;
import ar.edu.unju.fi.tpfinal.service.IEmployeeService;

public class EmployeeServiceImp implements IEmployeeService {

	
	
	
	private static final Log LOGGER = LogFactory.getLog(EmployeeServiceImp.class);
	
	private List<Employee> employeeList = new ArrayList<Employee>();
	
	
	
	@Autowired
	private Employee employee;
	
	@Override
	public void agregarEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> obtenerEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarEmployee(Long empoyeeNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee updateNuemeroDeEmpleado(Long employeeNumber) {
		// TODO Auto-generated method stub
		Employee e=new Employee();
		
		for (Employee emp : employeeList) {
			if(emp.getEmployeeNumber() == employeeNumber) {
			//	prod.setStock(prod.getStock()-cantidad);
				e = emp;
			}
		}
		return e;
	}

}

package ar.edu.unju.fi.tpfinal.service;

import java.util.List;

import ar.edu.unju.fi.tpfinal.model.Employee;

public interface IEmployeeService {
	public void agregarEmployee(Employee employee);
	
	public Employee getEmployee();
	
	public List<Employee> obtenerEmployees();
	
	public void eliminarEmployee(Long empoyeeNumber);
	
	public List<Employee> listaEmployeeSeleccionado();
	
	public List<Employee> buscarEmployeePorEmployeeNumber(Long employeeNumber);
	
	public void quitarEmployeeListaSeleccionado(Long employeeNumber);
}

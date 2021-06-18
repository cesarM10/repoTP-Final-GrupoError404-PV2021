package ar.edu.unju.fi.tpfinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.Employee;

public interface IEmployeeRepository extends CrudRepository<Employee, Long>{
	public Employee deleteByEmployeeNumber(Long employeeNumber);
	
	public List<Employee> findByEmployeeNumber(Long employeeNumber); 
}

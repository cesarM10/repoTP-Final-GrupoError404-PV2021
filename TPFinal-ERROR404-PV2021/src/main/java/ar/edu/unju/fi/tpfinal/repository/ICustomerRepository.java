package ar.edu.unju.fi.tpfinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import ar.edu.unju.fi.tpfinal.model.Customer;

public interface ICustomerRepository extends CrudRepository<Customer, Long>{ //Long es nuestro identificador
	
	public List<Customer> findByCustomerNumber(Long customerNumber);
}

package ar.edu.unju.fi.tpfinal.service;

import java.util.List;

import ar.edu.unju.fi.tpfinal.model.Customer;



public interface ICustomerService {
	public void generarTablaCustomer();
	
	public void agregarCustomer(Customer customer);
	
	public List<Customer> obtenerCustomer();

}

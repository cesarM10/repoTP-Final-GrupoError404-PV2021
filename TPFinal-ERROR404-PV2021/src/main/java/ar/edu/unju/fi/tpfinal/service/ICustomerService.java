package ar.edu.unju.fi.tpfinal.service;

import java.util.List;

import ar.edu.unju.fi.tpfinal.model.Customer;



public interface ICustomerService {
	public void generarTablaCliente();
	
	public void agregarCliente(Customer cliente);
	
	public List<Customer> obtenerClientes();

}

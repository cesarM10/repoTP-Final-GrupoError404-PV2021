package ar.edu.unju.fi.tpfinal.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.tpfinal.model.Customer;


public interface ICustomerService {
	public void generarTablaCustomer();
	
	public void agregarCustomer(Customer customer);
	
	public List<Customer> obtenerCustomer();
	
	//para eliminar  modificar 
	
	public Customer getCustomer();
	
	public Optional <Customer> getCustomerPorId(Long customerNumber);
	
	public void eliminarCustomer(Long customerNumber);
	
	public List<Customer> listaCustomerSeleccionado();
	
	public List<Customer> buscarCustomerPorCustomerNumber(Long customerNumber);

}

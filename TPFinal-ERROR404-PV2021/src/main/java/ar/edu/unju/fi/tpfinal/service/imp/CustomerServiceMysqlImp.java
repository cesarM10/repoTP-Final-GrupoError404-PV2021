package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Customer;
import ar.edu.unju.fi.tpfinal.repository.ICustomerRepository;
import ar.edu.unju.fi.tpfinal.service.ICustomerService;

@Service("customerServiceMysql")
public class CustomerServiceMysqlImp implements ICustomerService{

	public List<Customer> customerSeleccionado = new ArrayList<Customer>();
	
	@Autowired
	private Customer customer;
	
	@Autowired
	private ICustomerRepository customerRepository; //objeto implementador.
	
	//Metodos
	
	
	@Override
	public void generarTablaCustomer() {
		
	}
   
	/**
	 * Metodo pasa salvar al customer
	 */
	@Override
	public void agregarCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	/**
	 * Metodo  lista de los customer
	 */
	@Override
	public List<Customer> obtenerCustomer() { 
		// TODO Auto-generated method stub
		List<Customer> customers = (List<Customer>) customerRepository.findAll();//lista de customer.
		return customers; 
	}

	/**
	 * Motodo que retorna al customer
	 */
	@Override
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Metodo para recuperar al empleado.
	 */
	@Override
	public Optional<Customer> getCustomerPorId(Long customerNumber) { //metodo que recupera al empleado.
		Optional<Customer> customers = customerRepository.findById(customerNumber);
		return customers;//retorna al customer.
	}

	/**
	 * Metodo para eliminar al customer.
	 */
	@Override
	public void eliminarCustomer(Long customerNumber) { 
		// TODO Auto-generated method stub
		customerRepository.deleteById(customerNumber);
	}
	
	@Override
	public List<Customer> listaCustomerSeleccionado() {
		
		return customerSeleccionado;
	}

	
	
	
	
	@Override
	public List<Customer> buscarCustomerPorCustomerNumber(Long customerNumber) {
		if(customerNumber == null) {
					
		}else {
			customerSeleccionado = customerRepository.findByCustomerNumber(customerNumber);
			
		}
		return customerSeleccionado;
	}

}

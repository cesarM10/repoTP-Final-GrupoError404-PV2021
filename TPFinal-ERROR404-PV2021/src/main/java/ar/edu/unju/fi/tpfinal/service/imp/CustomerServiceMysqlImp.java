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
	private ICustomerRepository customerRepository;
	
	//Metodos
	@Override
	public void generarTablaCustomer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
	}

	@Override
	public List<Customer> obtenerCustomer() {
		// TODO Auto-generated method stub
		List<Customer> customers = (List<Customer>) customerRepository.findAll();
		return customers;
	}

	@Override
	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return customer;
	}

	@Override
	public Optional<Customer> getCustomerPorId(Long customerNumber) {
		// TODO Auto-generated method stub
		Optional<Customer> customers = customerRepository.findById(customerNumber);
		return customers;
	}

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

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
   
	@Override
	public void agregarCustomer(Customer customer) {//metodo para guardar el empleado
		customerRepository.save(customer);//guarda el objeto de la clase customer.
	}

	@Override
	public List<Customer> obtenerCustomer() { //metodo que permitia cargar la lista customer.
		// TODO Auto-generated method stub
		List<Customer> customers = (List<Customer>) customerRepository.findAll();//lista de customer.
		return customers; //retorna la lista.
	}

	@Override
	public Customer getCustomer() {
		return customer;//retorna al customer.
	}

	@Override
	public Optional<Customer> getCustomerPorId(Long customerNumber) { //metodo que recupera al empleado.
		Optional<Customer> customers = customerRepository.findById(customerNumber);
		return customers;//retorna al customer.
	}

	@Override
	public void eliminarCustomer(Long customerNumber) { //metodo para eliminar.
		// TODO Auto-generated method stub
		customerRepository.deleteById(customerNumber);//recibe  customerNumber para eliminar 
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

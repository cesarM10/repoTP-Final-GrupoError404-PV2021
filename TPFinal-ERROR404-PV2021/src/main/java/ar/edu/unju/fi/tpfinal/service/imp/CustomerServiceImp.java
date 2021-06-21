package ar.edu.unju.fi.tpfinal.service.imp;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Customer;
import ar.edu.unju.fi.tpfinal.service.ICustomerService;
import ar.edu.unju.fi.tpfinal.util.TablaCustomer;

@Service("customerUtilService")
public class CustomerServiceImp implements ICustomerService {

	private static final Log LOGGER = LogFactory.getLog(CustomerServiceImp.class);
	private List<Customer> customerList= new ArrayList<Customer>();//ArrayList
	
	
	
	@Autowired
	private Customer customer;
	
	//metodos para agregar y guardar
	@Override
	public void generarTablaCustomer() {
		
		customerList= TablaCustomer.listaCustomer;
		
		LOGGER.info("METHOD: generarTablaCustomer - creo primer customer por defecto" + customerList.get(customerList.size()-1));
	}

	@Override
	public void agregarCustomer(Customer customer) {
		// Agrega un customer a la lista de customer
		customerList.add(customer);
	LOGGER.info("METHOD: agregarCustomer - se agrego un objeto Customer a la lista ->" +  customerList.get(customerList.size()-1));		
	}

	@Override
	public List<Customer> obtenerCustomer() {
		// TODO Auto-generated method stub
		LOGGER.info("METHOD: obtenerCustomer - se recupero la lista de Objeto Customer");
		return customerList;
	}

	//metodos para la eliminacion y modificación
	@Override
	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return customer;
	}

	@Override
	public Optional<Customer> getCustomerPorId(Long customerNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarCustomer(Long customerNumber) {
		// TODO Auto-generated method stub
		
	}



}

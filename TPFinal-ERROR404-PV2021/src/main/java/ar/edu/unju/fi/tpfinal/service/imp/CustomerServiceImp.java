package ar.edu.unju.fi.tpfinal.service.imp;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Customer;
import ar.edu.unju.fi.tpfinal.service.ICustomerService;
import ar.edu.unju.fi.tpfinal.util.TablaCustomer;

@Service("customerUtilService")
public class CustomerServiceImp implements ICustomerService {

	private static final Log LOGGER = LogFactory.getLog(CustomerServiceImp.class);
	private List<Customer> customerList;
	//metodos
	@Override
	public void generarTablaCustomer() {
		// TODO Auto-generated method stub
		customerList= TablaCustomer.listaCustomer;
	customerList.add(new Customer(1221.12,qq,sfs,cdzcvfv,qq,qq,vsf,cdcd,fvfs,12,vv,wswa,2.1));
		
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



}

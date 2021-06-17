package ar.edu.unju.fi.tpfinal.service.imp;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Customer;
import ar.edu.unju.fi.tpfinal.service.ICustomerService;

@Service("clienteUtilService")
public class CustomerServiceImp implements ICustomerService {

	@Override
	public void generarTablaCliente() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarCliente(Customer cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> obtenerClientes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/*
	
	private static final Log LOGGER = LogFactory.getLog(CustomerServiceImp.class);
	private List<Cliente> clienteList;
	
	@Override
	public void generarTablaCliente() {
		clienteList = TablaCliente.listaClientes;
		clienteList.add(new Cliente("DNI",1234,"Cesar Mercado","cesarm10@gmail.com","01234",LocalDate.of(1992, 04, 10),3886,617729,LocalDate.of(2021, 03, 17)));
		clienteList.add(new Cliente("Pasaporte",9987,"Enrique Rodriguez","enrique@gmail.com","9122018",LocalDate.of(1994, 07, 3),388,765234,LocalDate.of(2020, 01, 10)));
		clienteList.add(new Cliente("DNI",5467,"Ivan Salas","ivan123@gmail.com","pass123",LocalDate.of(1997, 12, 1),355,543643,LocalDate.of(2021, 05, 10)));
		clienteList.add(new Cliente("Pasaporte",6744,"Gaspar Alvaro","alvaro678@gmail.com","P677ss",LocalDate.of(1995, 01, 13),399,56246,LocalDate.of(2018, 12, 9)));
		LOGGER.info("METHOD: generarTablaCliente - creo primer cliente por defecto" + clienteList.get(clienteList.size()-1));

	}

	@Override
	public void agregarCliente(Cliente cliente) {
		// agrego un cliente a la lista de clientes
		
		clienteList.add(cliente);
		LOGGER.info("METHOD: agregarCliente - se agrego un objeto Cliente a la lista ->" + clienteList.get(clienteList.size()-1));

	}

	@Override
	public List<Cliente> obtenerClientes() {
		LOGGER.info("METHOD: obtenerClientes - se recupero la lista de Objeto Cliente");
		
		return clienteList;
	}
	
	*/

}

package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Order;
import ar.edu.unju.fi.tpfinal.repository.IOrderRepository;
import ar.edu.unju.fi.tpfinal.service.IOrderService;

@Service("orderServiceMysql")
public class OrderServiceMysql implements IOrderService{

	@Autowired
	private Order order;
	
	@Autowired
	private IOrderRepository orderRepository; //inyecion de metodos que implementa la base de datos.
	
	
	/**
	 * Agrega un objeto del tipo Order a la base de datos.
	 */
	@Override
	public void agregarOrder(Order orderNumber) {
		orderRepository.save(orderNumber);
		
	}

	/**
	 * Devuelve un nuevo objeto del tipo Order.
	 */
	@Override
	public Order getOrder() {
		
		return order;
	}

	/**
	 * Devuelve una lista con todos las ordenes en la base de datos.
	 */
	@Override
	public List<Order> obtenerOrder() {
		List<Order> orders = (List<Order>)orderRepository.findAll();
		return orders;
	}

	/**
	 * Elimina un objeto del tipo Order de la base de datos.
	 */
	@Override
	public void eliminarOrder(Long orderNumber) {
		orderRepository.deleteById(orderNumber);
		
	}

}

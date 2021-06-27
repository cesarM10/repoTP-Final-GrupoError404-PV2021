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
	private IOrderRepository orderRepository;
	
	@Override
	public void agregarOrder(Order orderNumber) {
		orderRepository.save(orderNumber);
		
	}

	@Override
	public Order getOrder() {
		
		return order;
	}

	@Override
	public List<Order> obtenerOrder() {
		List<Order> orders = (List<Order>)orderRepository.findAll();
		return orders;
	}

	@Override
	public void eliminarOrder(Long orderNumber) {
		orderRepository.deleteById(orderNumber);
		
	}

}

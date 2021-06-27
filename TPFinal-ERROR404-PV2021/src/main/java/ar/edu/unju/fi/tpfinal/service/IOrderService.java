package ar.edu.unju.fi.tpfinal.service;

import java.util.List;

import ar.edu.unju.fi.tpfinal.model.Order;

public interface IOrderService {
	public void agregarOrder(Order orderNumber);
	
	public Order getOrder();
	
	public List<Order> obtenerOrder();
	
	public void eliminarOrder(Long orderNumber);
}

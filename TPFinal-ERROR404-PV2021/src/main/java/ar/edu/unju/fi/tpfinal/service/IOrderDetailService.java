package ar.edu.unju.fi.tpfinal.service;

import java.util.List;

import ar.edu.unju.fi.tpfinal.model.OrderDetail;

public interface IOrderDetailService {
	
	public void agregarOrderDetail(OrderDetail id);
	
	public OrderDetail getOrderDetail();
	
	public List<OrderDetail> obtenerOrderDetails();
	
	public void eliminarOrderDetail(Long id);
	
	public List<OrderDetail> buscarProductosPorOrderNumber(int orderLineNumber);
}

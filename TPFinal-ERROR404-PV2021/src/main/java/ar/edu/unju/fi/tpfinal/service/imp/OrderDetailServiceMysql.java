package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.OrderDetail;
import ar.edu.unju.fi.tpfinal.repository.IOrderDetailRepository;
import ar.edu.unju.fi.tpfinal.service.IOrderDetailService;

@Service("orderDetailServiceMysql")
public class OrderDetailServiceMysql implements IOrderDetailService{

	@Autowired
	private OrderDetail orderDetail = new OrderDetail(); 
	
	@Autowired
	private IOrderDetailRepository orderDetailRepository; //inyecion de metodos que implementa la base de datos.
	
	
	/**
	 * Agrega un objeto del tipo OrderDetail a la base de datos.
	 */
	@Override
	public void agregarOrderDetail(OrderDetail id) {
		orderDetailRepository.save(id);
		
	}

	/**
	 * Devuelve un nuevo objeto del tipo OrderDetail.
	 */
	@Override
	public OrderDetail getOrderDetail() {
		
		return orderDetail;
	}

	/**
	 * Devuelve una lista con las OrderDetail de la base de datos.
	 */
	@Override
	public List<OrderDetail> obtenerOrderDetails() {
		List<OrderDetail> orderDetails = (List<OrderDetail>) orderDetailRepository.findAll();
		return orderDetails;
	}

	/**
	 * Elimina un objeto OrderDetail de la base de datos.
	 */
	@Override
	public void eliminarOrderDetail(Long id) {
		orderDetailRepository.deleteById(id);
	}

	/**
	 * Retorna una lista con el OrderDetail buscado por su secuencia
	 * de generacion de factura.
	 */
	@Override
	public List<OrderDetail> buscarProductosPorOrderNumber(int orderLineNumber) {
		List<OrderDetail> productosEnOrder = orderDetailRepository.findByOrderLineNumber(orderLineNumber);
		return productosEnOrder;
	}

	

}

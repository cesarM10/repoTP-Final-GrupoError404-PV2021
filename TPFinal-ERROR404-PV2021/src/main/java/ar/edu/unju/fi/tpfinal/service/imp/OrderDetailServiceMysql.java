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
	private OrderDetail orderDetail;
	
	@Autowired
	private IOrderDetailRepository orderDetailRepository;
	
	@Override
	public void agregarOrderDetail(OrderDetail id) {
		orderDetailRepository.save(id);
		
	}

	@Override
	public OrderDetail getOrderDetail() {
		
		return orderDetail;
	}

	@Override
	public List<OrderDetail> obtenerOrderDetails() {
		List<OrderDetail> orderDetails = (List<OrderDetail>) orderDetailRepository.findAll();
		return orderDetails;
	}

	@Override
	public void eliminarOrderDetail(Long id) {
		orderDetailRepository.deleteById(id);
	}

	@Override
	public List<OrderDetail> buscarProductosPorOrderNumber(int orderLineNumber) {
		List<OrderDetail> productosEnOrder = orderDetailRepository.findByOrderLineNumber(orderLineNumber);
		return productosEnOrder;
	}

	

}

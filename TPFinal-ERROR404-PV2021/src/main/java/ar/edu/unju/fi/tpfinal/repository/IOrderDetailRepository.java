package ar.edu.unju.fi.tpfinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.OrderDetail;

public interface IOrderDetailRepository extends CrudRepository<OrderDetail, Long>{
	public List<OrderDetail> findByOrderLineNumber(int orderLineNumber);
}

/**
 * 
 */
package ar.edu.unju.fi.tpfinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.Product;
 

/**
 * @author Alvaro
 *
 */
public interface IProductRepository  extends CrudRepository<Product, Long>{
	
	public List<Product> findByProductCode(Long productCode);
	
}

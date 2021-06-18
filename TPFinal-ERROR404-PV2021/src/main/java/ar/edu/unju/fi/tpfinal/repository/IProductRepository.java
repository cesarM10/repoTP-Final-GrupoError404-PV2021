/**
 * 
 */
package ar.edu.unju.fi.tpfinal.repository;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.Product;
 

/**
 * @author Alvaro
 *
 */
public interface IProductRepository  extends CrudRepository<Product, Long>{
	public Product findByProductCode(Long productCode);
	
}

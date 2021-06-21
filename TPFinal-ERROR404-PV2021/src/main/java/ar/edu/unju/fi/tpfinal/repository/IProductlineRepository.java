/**
 * 
 */
package ar.edu.unju.fi.tpfinal.repository;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.Productline;

/**
 * @author Alvaro
 *
 */
public interface IProductlineRepository extends CrudRepository<Productline, Long>{
	public Productline findByProductLine(Long productLine);
}
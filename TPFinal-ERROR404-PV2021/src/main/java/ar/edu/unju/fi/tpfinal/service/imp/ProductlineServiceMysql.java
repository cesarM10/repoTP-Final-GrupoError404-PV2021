/**
 * 
 */
package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import ar.edu.unju.fi.tpfinal.model.Productline;
import ar.edu.unju.fi.tpfinal.repository.IProductlineRepository;
import ar.edu.unju.fi.tpfinal.service.IProductlineService;

/**
 * @author Alvaro
 *
 */
@Service("productlineServiceMysql")
public class ProductlineServiceMysql implements IProductlineService {
	
	@Autowired
	private Productline productline;
	
	@Autowired
	private IProductlineRepository productlineRepository;

	@Override
	public void generarTablaProductoline() {
		// TODO Auto-generated method stub
		
	}
//agrega una categoria a la lista
	@Override
	public void agregarProductoline(Productline productline) {
		// TODO Auto-generated method stub
		productlineRepository.save(productline);
	}

	@Override
	public List<Productline> obtenerProductosline() {
		// TODO Auto-generated method stub
		List<Productline> productlines = (List<Productline>) productlineRepository.findAll();
		return productlines;
	}

	@Override
	public Productline getProductline() {
		// TODO Auto-generated method stub
		return productline;
	}

	@Override
	public Optional<Productline> getProductlinePorId(Long id) {
		// TODO Auto-generated method stub
		Optional<Productline> productline = productlineRepository.findById(id);
		return productline;
	}
//eliminar una categoria
	@Override
	public void eliminarProductline(Long id) {
		// TODO Auto-generated method stub
		productlineRepository.deleteById(id);
	}
 
}

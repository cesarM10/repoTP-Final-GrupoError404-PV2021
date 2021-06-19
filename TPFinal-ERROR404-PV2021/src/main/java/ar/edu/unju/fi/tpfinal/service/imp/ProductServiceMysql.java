/**
 * 
 */
package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import ar.edu.unju.fi.tpfinal.repository.IProductRepository;
import ar.edu.unju.fi.tpfinal.model.Product;
import ar.edu.unju.fi.tpfinal.service.IProductService;

/**
 * @author Alvaro
 *
 */
@Service("productServiceMysql")
public class ProductServiceMysql implements IProductService {

	@Autowired
	private Product product;
	
	@Autowired
	private IProductRepository productRepository;
	
	@Override
	public void generarTablaProducto() {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregarProducto(Product product) {
		// TODO Auto-generated method stub
		productRepository.save(product);
	}

	@Override
	public List<Product> obtenerProductos() {
		// TODO Auto-generated method stub
		List<Product> products = (List<Product>) productRepository.findAll();
		return products;
	}

	@Override
	public Product getProduct() {
		// TODO Auto-generated method stub
		return product;
	}

	@Override
	public Optional<Product> getProductPorId(Long id) {
		// TODO Auto-generated method stub
		Optional<Product> product = productRepository.findById(id);
		return product;
	}

	@Override
	public void eliminarProduct(Long id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
	}

}

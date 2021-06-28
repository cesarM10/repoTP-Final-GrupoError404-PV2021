/**
 * 
 */
package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.util.TablaProduct; 
import ar.edu.unju.fi.tpfinal.model.Product;
import ar.edu.unju.fi.tpfinal.service.IProductService;

/**
 * @author Alvaro
 *
 */
@Service("productUtilService")
public class ProductServiceImp implements IProductService {
	
	private static final Log LOGGER = LogFactory.getLog(ProductServiceImp.class);
	
	private List<Product> productList = new ArrayList<Product>();

	@Autowired
	private Product product;
	
	@Override
	public void generarTablaProducto() {
		// TODO Auto-generated method stub
		productList = TablaProduct.listaProductos;
		 LOGGER.info("METHOD: generarTablaProduct - crea productos por defecto");

	}

	@Override
	public void agregarProducto(Product product) {
		// TODO Auto-generated method stub
		productList.add(product);
		LOGGER.info("METHOD: agregarProducto - se agrego un objeto Producto a la lista -> "+ productList.get(productList.size()-1));

	}

	@Override
	public Product getProduct() {
		// TODO Auto-generated method stub
		return product;
	}
	
	@Override
	public List<Product> obtenerProductos() {
		// TODO Auto-generated method stub
		LOGGER.info("METHOD: obtenerProductos - se recupero la lista de Objeto Producto");
		return productList;
	}


	@Override
	public Optional<Product> getProductPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarProduct(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> listaDeProductos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarListaDeProductos(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> buscarProductPorProductCode(Long productCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateStockPorProductCode(Long productCode, int cantidad) {
		// TODO Auto-generated method stub
		return null;
	}

}

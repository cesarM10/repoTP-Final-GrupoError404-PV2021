/**
 * 
 */
package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	
	private List<Product> productList;

	@Override
	public void generarTablaProducto() {
		// TODO Auto-generated method stub
		productList = TablaProduct.listaProductos;
		//productList.add(new Producto(3,"Juego PC F1 2021",1899.99,"CodeMasters",100));
		LOGGER.info("METHOD: generarTablaProduct - crea productos por defecto");

	}

	@Override
	public void agregarProducto(Product product) {
		// TODO Auto-generated method stub
		productList.add(product);
		LOGGER.info("METHOD: agregarProducto - se agrego un objeto Producto a la lista -> "+ productList.get(productList.size()-1));

	}

	/*@Override
	public Product ultimoProducto() {
		// TODO Auto-generated method stub
		 LOGGER.info("METHOD: ultimoProducto - se muestra el ultimo objeto Producto a la lista -> "+ productList.get(productList.size()-1));
		 return productList.get(productList.size()-1);
	}
*/
	@Override
	public List<Product> obtenerProductos() {
		// TODO Auto-generated method stub
		LOGGER.info("METHOD: obtenerProductos - se recupero la lista de Objeto Producto");
		return productList;
	}

}

/**
 * 
 */
package ar.edu.unju.fi.tpfinal.service;

import java.util.List;
import java.util.Optional;
 
import ar.edu.unju.fi.tpfinal.model.Product;

/**
 * @author Alvaro
 *
 */
public interface IProductService {

	public void generarTablaProducto();//
	
	public void agregarProducto(Product product);//
	 
	
	public List<Product> obtenerProductos();//
	
	public Product getProduct();
	
	public Optional <Product> getProductPorId(Long id);
	
	public void eliminarProduct(Long id); 
	
	public List<Product> listaDeProductos();
	
	public void agregarListaDeProductos(Product product);
	
	public List<Product> buscarProductPorProductCode(Long productCode);
	
	/*Object obtenerProductos();

	void agregarProducto(Product product);

	Object ultimoProducto();

	void generarTablaProducto();*/

}

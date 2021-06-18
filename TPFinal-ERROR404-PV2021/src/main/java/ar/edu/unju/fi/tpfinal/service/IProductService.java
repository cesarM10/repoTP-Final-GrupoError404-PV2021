/**
 * 
 */
package ar.edu.unju.fi.tpfinal.service;

import java.util.List;
 
import ar.edu.unju.fi.tpfinal.model.Product;

/**
 * @author Alvaro
 *
 */
public interface IProductService {

	public void generarTablaProducto();//
	
	public void agregarProducto(Product product);//
	 
	
	public List<Product> obtenerProductos();//
	
	 
	
	 
	/*Object obtenerProductos();

	void agregarProducto(Product product);

	Object ultimoProducto();

	void generarTablaProducto();*/

}

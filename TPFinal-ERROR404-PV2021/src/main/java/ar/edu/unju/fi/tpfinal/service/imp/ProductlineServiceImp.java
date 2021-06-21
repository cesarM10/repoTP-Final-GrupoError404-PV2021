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
 
import ar.edu.unju.fi.tpfinal.model.Productline;
import ar.edu.unju.fi.tpfinal.service.IProductlineService; 
import ar.edu.unju.fi.tpfinal.util.TablaProductline;

/**
 * @author Alvaro
 *
 */
@Service("productlineUtilService")
public class ProductlineServiceImp implements IProductlineService {

	 private static final Log LOGGER = LogFactory.getLog(ProductlineServiceImp.class);
		
	 private List<Productline> productlineList = new ArrayList<Productline>();
		
	 @Autowired
	 private Productline productline;

	@Override
	public void generarTablaProductoline() {
		// TODO Auto-generated method stub
		productlineList = TablaProductline.listaProductosline;
		 LOGGER.info("METHOD: generarTablaProductline - crea linea de productos por defecto");
	}

	@Override
	public void agregarProductoline(Productline productline) {
		// TODO Auto-generated method stub
		productlineList.add(productline);
		LOGGER.info("METHOD: agregarProductoline - se agrego un objeto Productoline a la lista -> "+ productlineList.get(productlineList.size()-1));

	}

	@Override
	public List<Productline> obtenerProductosline() {
		// TODO Auto-generated method stub
		LOGGER.info("METHOD: obtenerProductosline - se recupero la lista de Objeto linea de Producto");
		return productlineList;
	}

	@Override
	public Productline getProductline() {
		// TODO Auto-generated method stub
		return productline;
	}

	@Override
	public Optional<Productline> getProductlinePorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarProductline(Long id) {
		// TODO Auto-generated method stub
		
	}
 
}

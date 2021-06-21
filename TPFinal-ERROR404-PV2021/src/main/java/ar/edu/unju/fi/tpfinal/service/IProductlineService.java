/**
 * 
 */
package ar.edu.unju.fi.tpfinal.service;

import java.util.List;
import java.util.Optional;
 
import ar.edu.unju.fi.tpfinal.model.Productline;

/**
 * @author Alvaro
 *
 */
public interface IProductlineService {
	
	public void generarTablaProductoline();//
	
	public void agregarProductoline(Productline productline);//void agregarProductoline(Productline productline);
	 
	
	public List<Productline> obtenerProductosline();//Object obtenerProductosline();
	
	public Productline getProductline();//Object getProductline();
	 
	
	public Optional <Productline> getProductlinePorId(Long id);//Optional<Productline> getProductlinePorId(Long id);
	
	public void eliminarProductline(Long id); //void eliminarProductline(Long id);
 
 
}

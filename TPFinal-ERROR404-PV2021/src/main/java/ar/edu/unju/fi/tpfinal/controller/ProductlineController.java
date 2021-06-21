/**
 * 
 */
package ar.edu.unju.fi.tpfinal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tpfinal.model.Productline;
import ar.edu.unju.fi.tpfinal.service.IProductlineService;

/**
 * @author Alvaro
 *
 */
@Controller
public class ProductlineController {

	@Autowired
	@Qualifier("productlineServiceMysql")
	private IProductlineService productlineService;
	
	@GetMapping("/productoline/nuevo")
	public String getNuevoProductPage(Model model) {
		//model.addAttribute(product);
		model.addAttribute("productline", productlineService.getProductline());//.getCliente());
		return "alta_productline";
	}
	
	@PostMapping("/productoline/guardar")
	public ModelAndView agregarProductolinePage(@ModelAttribute("productoline")Productline productline) {
		ModelAndView model = new ModelAndView("lista_productline");
		/*if (productService.obtenerProductos() == null) {
			productService.generarTablaProducto();
		}*/
		productlineService.agregarProductoline(productline);
		
		model.addObject("productline", productlineService.obtenerProductosline());
		return model;
	} 
	
	@GetMapping("/productoline/listado")
	public ModelAndView getProductoslinePage(){
		ModelAndView model = new ModelAndView("lista_productline");
		/*if(productService.obtenerProductos() == null) {
			productService.generarTablaProducto();
		}*/
		
		model.addObject("productline", productlineService.obtenerProductosline());
		return model;
	}
	
	///////////
	@GetMapping("/productoline/editar/{id}")
	public ModelAndView getProductolineEditPage(@PathVariable(value = "id")Long id) {
		//LOGGER.info("METODO - - EDITAR Product");
		ModelAndView model = new ModelAndView("alta_productline");
		Optional <Productline> productline = productlineService.getProductlinePorId(id);
		
		model.addObject("productline", productline);
		return model;
	}
	
	@GetMapping("/productoline/eliminar/{id}")
	public ModelAndView getProductolineDeletePage(@PathVariable(value = "id")Long id) {
		ModelAndView model = new ModelAndView("redirect:/productoline/listado");
		productlineService.eliminarProductline(id);
		
		return model;
	}
 
}

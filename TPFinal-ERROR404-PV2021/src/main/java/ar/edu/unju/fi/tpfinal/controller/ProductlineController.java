/**
 * 
 */
package ar.edu.unju.fi.tpfinal.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tpfinal.model.Productline;
import ar.edu.unju.fi.tpfinal.service.IProductlineService;
import ar.edu.unju.fi.tpfinal.service.imp.ProductlineServiceImp;

/**
 * @author Alvaro
 *
 */
@Controller
public class ProductlineController {

	private static final Log LOGGER = LogFactory.getLog(ProductlineServiceImp.class);	
	
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
	public ModelAndView agregarProductolinePage(@Valid @ModelAttribute("productoline")Productline productline, BindingResult resultadoValidacion) {

		/*if (productService.obtenerProductos() == null) {
			productService.generarTablaProducto();
		}*/
	/*	ModelAndView model = new ModelAndView("lista_productline");
		productlineService.agregarProductoline(productline);
		
		model.addObject("productline", productlineService.obtenerProductosline());
		return model;*/
		LOGGER.info("Metodo: guardando... --");
		ModelAndView model;
		if(resultadoValidacion.hasErrors()) { //encontró errores.
			model = new ModelAndView("alta_productline");
			model.addObject("productline", productline); 	
			return model;
		}else { //no encontró errores.
			model = new ModelAndView("lista_productline");
			
			productlineService.agregarProductoline(productline);
			
			model.addObject("productline", productlineService.obtenerProductosline());
			return model;
		}
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

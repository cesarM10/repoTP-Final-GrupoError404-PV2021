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
 
import ar.edu.unju.fi.tpfinal.model.Product;
import ar.edu.unju.fi.tpfinal.service.IProductService;
import ar.edu.unju.fi.tpfinal.service.IProductlineService;
import ar.edu.unju.fi.tpfinal.service.imp.ProductServiceImp;

@Controller
public class ProductController {
	private static final Log LOGGER = LogFactory.getLog(ProductServiceImp.class);	
	
	/*@GetMapping("/producto/nuevo")
	public String getNuevoProductoPage(){ 

		return "alta_product";
	} */
	
 	/*@Autowired
	@Qualifier("productUtilService")
	private IProductService productService;
	
	@Autowired
	@Qualifier("productObject")
	private Product product;*/
	
	@Autowired
	@Qualifier("productServiceMysql")
	private IProductService productService;
	
	@Autowired
	@Qualifier("productlineServiceMysql")
	private IProductlineService productlineService;
	
	@GetMapping("/producto/nuevo")
	public String getNuevoProductPage(Model model) {
		//model.addAttribute(product);
		model.addAttribute("product", productService.getProduct());//.getCliente());
		model.addAttribute("productLines", productlineService.obtenerProductosline());
		return "alta_product";
	}
	
	@PostMapping("/producto/guardar")
	public ModelAndView agregarProductoPage(@Valid@ModelAttribute("producto")Product product, BindingResult resultadoValidacion) {
		/*if (productService.obtenerProductos() == null) {
			productService.generarTablaProducto();
		}*/
		/*ModelAndView model = new ModelAndView("lista_product");
		
		productService.agregarProducto(product);
		
		model.addObject("product", productService.obtenerProductos());
		return model;*/
		LOGGER.info("Metodo: guardando... --");
		ModelAndView model;
		if(resultadoValidacion.hasErrors()) { //encontró errores.
			model = new ModelAndView("alta_product");
			model.addObject("product", product); 	
			return model;
		}else { //no encontró errores.
			model = new ModelAndView("lista_product");
			
			productService.agregarProducto(product);
			
			model.addObject("product", productService.obtenerProductos());
			return model;
		}
	} 
	
	@GetMapping("/producto/listado")
	public ModelAndView getProductosPage(){
		ModelAndView model = new ModelAndView("lista_product");
		/*if(productService.obtenerProductos() == null) {
			productService.generarTablaProducto();
		}*/
		
		model.addObject("product", productService.obtenerProductos());
		return model;
	}
	
	///////////
	@GetMapping("/producto/editar/{id}")
	public ModelAndView getClienteEditPage(@PathVariable(value = "id")Long id) {
		//LOGGER.info("METODO - - EDITAR Product");
		ModelAndView model = new ModelAndView("alta_product");
		Optional <Product> product = productService.getProductPorId(id);
		
		model.addObject("product", product);
		model.addObject("productLines", productlineService.obtenerProductosline());
		return model;
	}
	
	@GetMapping("/producto/eliminar/{id}")
	public ModelAndView getClienteDeletePage(@PathVariable(value = "id")Long id) {
		ModelAndView model = new ModelAndView("redirect:/producto/listado");
		productService.eliminarProduct(id);
		
		return model;
	}
 
}

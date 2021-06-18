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
 
import ar.edu.unju.fi.tpfinal.model.Product;
import ar.edu.unju.fi.tpfinal.service.IProductService;

@Controller
public class ProductController {

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
	
	@GetMapping("/producto/nuevo")
	public String getNuevoProductPage(Model model) {
		//model.addAttribute(product);
		model.addAttribute("product", productService.getProduct());//.getCliente());
		return "alta_product";
	}
	
	@PostMapping("/producto/guardar")
	public ModelAndView agregarProductoPage(@ModelAttribute("producto")Product product) {
		ModelAndView model = new ModelAndView("lista_product");
		/*if (productService.obtenerProductos() == null) {
			productService.generarTablaProducto();
		}*/
		productService.agregarProducto(product);
		
		model.addObject("product", productService.obtenerProductos());
		return model;
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
		return model;
	}
	
	@GetMapping("/producto/eliminar/{id}")
	public ModelAndView getClienteDeletePage(@PathVariable(value = "id")Long id) {
		ModelAndView model = new ModelAndView("redirect:/producto/listado");
		productService.eliminarProduct(id);
		
		return model;
	}
 
}

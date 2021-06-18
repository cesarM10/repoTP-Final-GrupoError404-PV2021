package ar.edu.unju.fi.tpfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@Autowired
	@Qualifier("productUtilService")
	private IProductService productService;
	
	@Autowired
	@Qualifier("productObject")
	private Product product;
	
	@GetMapping("/producto/nuevo")
	public String getNuevoProductPage(Model model) {
		model.addAttribute(product);
		return "alta_product";
	}
	
	@PostMapping("/producto/guardar")
	public ModelAndView agregarProductoPage(@ModelAttribute("producto")Product product) {
		ModelAndView model = new ModelAndView("lista_product");
		if (productService.obtenerProductos() == null) {
			productService.generarTablaProducto();
		}
		productService.agregarProducto(product);
		
		model.addObject("product", productService.obtenerProductos());
		return model;
	}
	/*
	@GetMapping("/producto/ultimo")
	public ModelAndView ultimoProductoPage() {
		ModelAndView modelView = new ModelAndView("lista_product");
		if(productService.obtenerProductos() == null) {
			productService.generarTablaProducto();
		}
		modelView.addObject("producto", productService.ultimoProducto());
		
		return modelView;
	}
	*/
	@GetMapping("/producto/listado")
	public ModelAndView getProductosPage(){
		ModelAndView model = new ModelAndView("lista_product");
		if(productService.obtenerProductos() == null) {
			productService.generarTablaProducto();
		}
		
		model.addObject("product", productService.obtenerProductos());
		return model;
	}
	

}

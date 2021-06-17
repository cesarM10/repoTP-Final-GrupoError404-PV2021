package ar.edu.unju.fi.tpfinal.controller;
/*
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;*/
import org.springframework.ui.Model;
/*import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;*/

import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.fi.tpfinal.model.Product;
//import ar.edu.unju.fi.tpfinal.service.IProductService;


public class ProductController {

	/* @Autowired
	@Qualifier("productServiceMysql")
	private IProductService productService;*/
	 
	@GetMapping("/product/nuevo")
	public String getNuevoProductPage(Model model) {
		//model.addAttribute("product", productService.getProduct());
		return "alta_product";
	}
	/*
	 @Autowired
	@Qualifier("productoServiceMysql")
	private IProductoService productoService;
	
	
	@GetMapping("/producto/nuevo")
	public String getNuevoProductoPage(Model model) {
		model.addAttribute("producto", productoService.getProducto());
		return "nuevoproducto";
	}
	
	@PostMapping("/producto/guardar")
	public ModelAndView agregarProductoPage(@Valid @ModelAttribute("producto")Producto producto, BindingResult resultadoValidacion) {
		ModelAndView model;
		
		if(resultadoValidacion.hasErrors()) {
			model = new ModelAndView("nuevoproducto");
			
			return model;
		}else {
			model = new ModelAndView("productos");
			
			productoService.agregarProducto(producto);
			
			model.addObject("producto", productoService.obtenerProductos());
			
			return model;
		}
		
		
	}
	
	@GetMapping("/producto/ultimo")
	public ModelAndView ultimoProductoPage() {
		ModelAndView modelView = new ModelAndView("ultimoproducto");
		
		modelView.addObject("producto", productoService.ultimoProducto());
		
		return modelView;
	}
	
	@GetMapping("/producto/listado")
	public ModelAndView getProductosPage(){
		ModelAndView model = new ModelAndView("productos");
		
		
		model.addObject("producto", productoService.obtenerProductos());
		return model;
	}
	
	@GetMapping("/producto/editar/{codigo}")
	public ModelAndView getProductoEditPage(@PathVariable(value = "codigo")Long codigo) {
		ModelAndView model = new ModelAndView("nuevoproducto");
		Producto producto = productoService.getProductoPorCodigo(codigo);
		model.addObject("producto", producto);
		
		
		return model;
	}
	
	@GetMapping("/producto/eliminar/{codigo}")
	public ModelAndView getProductoDeletePage(@PathVariable(value = "codigo")Long codigo) {
		ModelAndView model = new ModelAndView("redirect:/producto/listado");
		productoService.eliminarProducto(codigo);
		
		return model;
	}
	 
	 */

}

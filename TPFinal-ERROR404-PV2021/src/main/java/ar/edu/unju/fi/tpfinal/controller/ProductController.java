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
import ar.edu.unju.fi.tpfinal.model.Productline;
import ar.edu.unju.fi.tpfinal.service.IProductService;
import ar.edu.unju.fi.tpfinal.service.IProductlineService;
import ar.edu.unju.fi.tpfinal.service.imp.ProductServiceMysql;
/**
 * @author 
 * Integrantes del Grupo Error404:
	 * Gaspar, Alvaro Emanuel
	 * Mercado, Cesar David
	 * Rodriguez, Enrique Sebastian
	 * Salas, Ivan Arnaldo
 */
@Controller
public class ProductController {
	private static final Log LOGGER = LogFactory.getLog(ProductServiceMysql.class);	
	
	/*@GetMapping("/producto/nuevo")
	public String getNuevoProductoPage(){ 

		return "alta_product";
	} */
	
 	/*@Autowired
	@Qualifier("productUtilService")
	private IProductService productService;
	*/
	@Autowired
	@Qualifier("productObject")
	private Product product; //ineccion del objeto produc
	
	@Autowired
	@Qualifier("productServiceMysql")
	private IProductService productService;//inyeccon del serviceproduct
	
	@Autowired
	@Qualifier("productlineServiceMysql")
	private IProductlineService productlineService;//inyeccon del serviceproductline
	/*
	 * crea un nuevo producto
	 */
	@GetMapping("/producto/nuevo")
	public String getNuevoProductPage(Model model) {
		//model.addAttribute(product);
		model.addAttribute("product", product);//.getCliente());
		model.addAttribute("productLines", productlineService.obtenerProductosline());
		return "alta_product";
	}
	/*
	 * gurda un producto una ves validado los campos 
	 */
	@PostMapping("/producto/guardar")
	public ModelAndView agregarProductoPage(@Valid @ModelAttribute("product")Product product, BindingResult resultadoValidacion) {
		/*if (productService.obtenerProductos() == null) {
			productService.generarTablaProducto();
		}*/
		/*ModelAndView model = new ModelAndView("lista_product");
		
		productService.agregarProducto(product);
		
		model.addObject("product", productService.obtenerProductos());
		return model;*/
		//LOGGER.info("Metodo: guardando... --");
		ModelAndView model;
				//validacion
		if(resultadoValidacion.hasErrors()) { //encontró errores.
			model = new ModelAndView("alta_product");
			//model.addObject("product", productService.getProduct());	
			model.addObject("productLines", productlineService.obtenerProductosline());
			LOGGER.info("Metodo: encontro error... --" + product);
			return model;
		}else { //no encontró errores.
			model = new ModelAndView("lista_product");
			LOGGER.info("Metodo: no encontro error... --");
			productService.agregarProducto(product);
			
			model.addObject("productLines", productService.obtenerProductos());
			
			return model;
		}
	} 
	/*
	 * genera lista de productos
	 */
	@GetMapping("/producto/listado")
	public ModelAndView getProductosPage(){
		ModelAndView model = new ModelAndView("lista_product");
		/*if(productService.obtenerProductos() == null) {
			productService.generarTablaProducto();
		}*/
		
		model.addObject("productLines", productService.obtenerProductos());
		return model;
	}
	
	///////////
	/*
	 * edita un producto de la lista
	 */
	@GetMapping("/producto/editar/{id}")
	public ModelAndView getProductoEditPage(@PathVariable(value = "id")Long id) {
		//LOGGER.info("METODO - - EDITAR Product");
		ModelAndView model = new ModelAndView("alta_product");
		Optional <Product> product = productService.getProductPorId(id);
		
		model.addObject("product", product);
		model.addObject("productLines", productlineService.obtenerProductosline());
		return model;
	}
	/*
	 * elimina un producto de la lista
	 */
	@GetMapping("/producto/eliminar/{id}")
	public ModelAndView getProductoDeletePage(@PathVariable(value = "id")Long id) {
		ModelAndView model = new ModelAndView("redirect:/producto/listado");
		productService.eliminarProduct(id);
		
		return model;
	}
 
}

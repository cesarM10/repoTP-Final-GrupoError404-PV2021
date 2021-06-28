/**
 * 
 */
package ar.edu.unju.fi.tpfinal.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	/*
	 * crear nueva categoria
	 */
	@GetMapping("/productoline/nuevo")
	public String getNuevoProductPage(Model model) {
		//model.addAttribute(product);
		model.addAttribute("productline", productlineService.getProductline());//.getCliente());
		return "alta_productline";
	}
	/*
	 * guarda los datos de la categoria cuando son validos
	 */
	@PostMapping("/productoline/guardar")
	public ModelAndView agregarProductolinePage(@Valid @ModelAttribute("productline")Productline productline, 
			@RequestParam("file") MultipartFile imagen,BindingResult resultadoValidacion) {

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
			//model.addObject("productline", productlineService.getProductline()); 	
			return model;
		}else { //no encontró errores.
			model = new ModelAndView("lista_productline");
	////////valida q se este recibiendo un ahivo q no este vacio
				if(!imagen.isEmpty()) {
					Path directorioImagenes= Paths.get("src//main//resources//static/image");//obtener ruta relativa
					String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();//ruta absoluta
					try {
						byte[]bytesImg=imagen.getBytes();//oteet o leer la imagen
						Path rutaCompleta= Paths.get(rutaAbsoluta+"//"+imagen.getOriginalFilename());//para obtener url completa de la ubicacion
						Files.write(rutaCompleta, bytesImg);
						productline.setImage(imagen.getOriginalFilename());//se envia el atributo de la imagen
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
				////////
			productlineService.agregarProductoline(productline);
			
			model.addObject("productline", productlineService.obtenerProductosline());
			return model;
		}
	} 
	/*
	 * muestra la lista de categorias creadas
	 */
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
	/*
	 * edicion de categoria
	 */
	@GetMapping("/productoline/editar/{id}")
	public ModelAndView getProductolineEditPage(@PathVariable(value = "id")Long id) {
		//LOGGER.info("METODO - - EDITAR Product");
		ModelAndView model = new ModelAndView("alta_productline");
		Optional <Productline> productline = productlineService.getProductlinePorId(id);
		
		model.addObject("productline", productline);
		return model;
	}
	/*
	 * elimina una categoria de la lista
	 */
	@GetMapping("/productoline/eliminar/{id}")
	public ModelAndView getProductolineDeletePage(@PathVariable(value = "id")Long id) {
		ModelAndView model = new ModelAndView("redirect:/productoline/listado");
		productlineService.eliminarProductline(id);
		
		return model;
	}
 
}

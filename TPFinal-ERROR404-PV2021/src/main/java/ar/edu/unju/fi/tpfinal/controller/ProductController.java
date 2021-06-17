package ar.edu.unju.fi.tpfinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

 
	@GetMapping("/producto/nuevo")
	public String getNuevoProductoPage(){ 

		return "alta_product";
	} 

}

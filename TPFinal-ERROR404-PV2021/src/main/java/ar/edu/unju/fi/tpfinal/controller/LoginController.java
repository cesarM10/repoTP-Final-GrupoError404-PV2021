package ar.edu.unju.fi.tpfinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping({"/","/login","/login?error=true"})
	public String cargarHome(Model model) {
		
		return "login";
	}

	
	
	@GetMapping("/logout")
	public String getLogoutPage() {
		return "logout";
	}
	
	
	@GetMapping("/index")
	public String getIndexPage() {
		return "index";
	}
	
	
	
	
	
	

	
	@GetMapping("/registro")
	public String getRegistroPage(Model model) {
		
		return "formulario_login";
	}
	

	

}

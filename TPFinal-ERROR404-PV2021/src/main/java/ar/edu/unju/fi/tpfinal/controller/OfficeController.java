package ar.edu.unju.fi.tpfinal.controller;

import java.util.Optional;

import javax.validation.Valid;

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

import ar.edu.unju.fi.tpfinal.model.Office;
import ar.edu.unju.fi.tpfinal.service.IOfficeService;

@Controller
public class OfficeController {
	
	@Autowired
	@Qualifier("officeServiceMysql")
	private IOfficeService officeService; 

	@GetMapping("/office/nuevo")
	public String getNuevaOficinaPage(Model model) {
		model.addAttribute("office", officeService.getOffice());
		
		return "alta_office";
	}
	
	@PostMapping("/office/guardar")
	public ModelAndView agregarOficinaPage(@Valid @ModelAttribute("office")Office office, BindingResult resultadoValidacion) {
		//ModelAndView model = new ModelAndView("lista_office");
		ModelAndView model;
		
		if(resultadoValidacion.hasErrors()) {
			model = new ModelAndView("alta_office");
			
			//model.addObject("office", officeService.getOffice());
			
			return model;
		}else {
			model = new ModelAndView("lista_office");
			
			officeService.agregarOffice(office);
			
			model.addObject("offices", officeService.obtenerOffices());
			
			return model;
		}
		
		
	}
	
	@GetMapping("/office/listado")
	public String getOficinasPage(Model model) {
		
		model.addAttribute("offices", officeService.obtenerOffices());
		return "lista_office";
	}
	
	@GetMapping("/office/editar/{officeCode}")
	public ModelAndView getCustomerEditPage(@PathVariable(value = "officeCode")Long officeCode) {
		
		ModelAndView model = new ModelAndView("alta_office");
		Optional <Office> office = officeService.getOfficePorCodigo(officeCode);
		
		model.addObject("office", office);
		
		return model;
	}
	
	@GetMapping("/office/eliminar/{officeCode}")
	public ModelAndView getCustomerDeletePage(@PathVariable(value = "officeCode")Long officeCode) {
		ModelAndView model = new ModelAndView("redirect:/office/listado");
		
		officeService.eliminarOffice(officeCode);
		
		model.addObject("offices",officeService.obtenerOffices());
		return model;
	}
}

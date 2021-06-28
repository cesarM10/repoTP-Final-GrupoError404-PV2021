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
/**
 * @author 
 * Integrantes del Grupo Error404:
	 * Gaspar, Alvaro Emanuel
	 * Mercado, Cesar David
	 * Rodriguez, Enrique Sebastian
	 * Salas, Ivan Arnaldo
 */
@Controller
public class OfficeController {
	
	@Autowired
	@Qualifier("officeServiceMysql")
	private IOfficeService officeService; //inyeccion OfficeService con metodos que trabaja con la base de datos.

	/**
	 * Metodo que agrega un objeto office necesario para el alta de un empleado.
	 * @param model
	 * @return Nos dirige al html alta_office
	 */
	@GetMapping("/office/nuevo")
	public String getNuevaOficinaPage(Model model) {
		model.addAttribute("office", officeService.getOffice());
		
		return "alta_office";
	}
	
	/**
	 *Metodo que recibe un office con los datos cargados en el hmtl de alta
	 *y los resultados de validaciones.
	 *Si tiene errores el resultadoValidacion nos retornara a alta_office, sino
	 *procede a guardar el office en la base de datos.
	 * @param office
	 * @param resultadoValidacion
	 * @return 
	 */
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
	
	/**
	 * Metodo que nos agrega a la vista de lista_office una lista cargada
	 * las oficinas que tenemos en la base de datos.
	 * @param model
	 * @return Dirige a lista_office.
	 */
	@GetMapping("/office/listado")
	public String getOficinasPage(Model model) {
		
		model.addAttribute("offices", officeService.obtenerOffices());
		return "lista_office";
	}
	
	/**
	 * Metodo que nos permite la buscar mediante officeCode una oficina en la 
	 * base de datos para modificar sus valores.
	 * @param officeCode
	 * @return Dirige a alta_office con los datos recuperados en los campos.
	 */
	@GetMapping("/office/editar/{officeCode}")
	public ModelAndView getCustomerEditPage(@PathVariable(value = "officeCode")Long officeCode) {
		
		ModelAndView model = new ModelAndView("alta_office");
		Optional <Office> office = officeService.getOfficePorCodigo(officeCode);
		
		model.addObject("office", office);
		
		return model;
	}
	
	/**
	 * Metodo que mediante un officeCode permite eliminar un office de la 
	 * base de datos.
	 * @param officeCode
	 * @return Redirige a la direccion /office/listado.
	 */
	@GetMapping("/office/eliminar/{officeCode}")
	public ModelAndView getCustomerDeletePage(@PathVariable(value = "officeCode")Long officeCode) {
		ModelAndView model = new ModelAndView("redirect:/office/listado");
		
		officeService.eliminarOffice(officeCode);
		
		model.addObject("offices",officeService.obtenerOffices());
		return model;
	}
}

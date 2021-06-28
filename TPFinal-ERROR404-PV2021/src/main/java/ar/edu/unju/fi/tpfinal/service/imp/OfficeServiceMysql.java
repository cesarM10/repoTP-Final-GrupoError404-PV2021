package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Office;
import ar.edu.unju.fi.tpfinal.repository.IOfficeRepository;
import ar.edu.unju.fi.tpfinal.service.IOfficeService;

@Service("officeServiceMysql")
public class OfficeServiceMysql implements IOfficeService{

	@Autowired
	private Office office = new Office();
	
	@Autowired
	private IOfficeRepository officeRepository;
	
	
	/**
	 * Agrega un objeto del tipo Office a la base de datos
	 */
	@Override
	public void agregarOffice(Office office) {
		officeRepository.save(office);
		
	}

	/**
	 * Devuelve un nuevo objeto del tipo Office
	 */
	@Override
	public Office getOffice() {
		return office;
	}

	/**
	 * Devuelve una lista con todas las oficinas en la base de datos.
	 */
	@Override
	public List<Office> obtenerOffices() {
		List<Office> offices = (List<Office>)officeRepository.findAll();
		return offices;
	}

	/**
	 * Elimina un objeto Office de la base de datos coincidente con 
	 * su clave primaria officeCode.
	 */
	@Override
	public void eliminarOffice(Long officeCode) {
		officeRepository.deleteById(officeCode);
		
	}

	/**
	 * Devuelve un objeto Office buscado en la base de datos por su
	 * clave primaria officeCode. Si no lo encuentra devuelve un 
	 * objeto vacio.
	 */
	@Override
	public Optional<Office> getOfficePorCodigo(Long officeCode) {
		Optional<Office> office = officeRepository.findById(officeCode);
		return office;
	}

}

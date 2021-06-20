package ar.edu.unju.fi.tpfinal.service;

import java.util.List;

import ar.edu.unju.fi.tpfinal.model.Office;

public interface IOfficeService {
	public void agregarEmployee(Office office);
	
	public Office getOffice();
	
	public List<Office> obtenerOffices();
	
	public void eliminarOffice(Long officeCode);
	
	public Office getOfficePorCodigo(Long OfficeCode);
}

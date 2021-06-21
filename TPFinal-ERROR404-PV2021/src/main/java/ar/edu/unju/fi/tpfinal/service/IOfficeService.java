package ar.edu.unju.fi.tpfinal.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.tpfinal.model.Office;

public interface IOfficeService {
	public void agregarOffice(Office office);
	
	public Office getOffice();
	
	public List<Office> obtenerOffices();
	
	public void eliminarOffice(Long officeCode);
	
	public Optional<Office> getOfficePorCodigo(Long OfficeCode);
}

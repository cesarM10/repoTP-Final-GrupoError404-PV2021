package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.List;

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
	
	@Override
	public void agregarEmployee(Office office) {
		officeRepository.save(office);
		
	}

	@Override
	public Office getOffice() {
		return office;
	}

	@Override
	public List<Office> obtenerOffices() {
		List<Office> offices = (List<Office>)officeRepository.findAll();
		return offices;
	}

	@Override
	public void eliminarOffice(Long officeCode) {
		officeRepository.deleteByOfficeCode(officeCode);
		
	}

	@Override
	public Office getOfficePorCodigo(Long officeCode) {
		Office office = officeRepository.findByOfficeCode(officeCode);
		return office;
	}

}

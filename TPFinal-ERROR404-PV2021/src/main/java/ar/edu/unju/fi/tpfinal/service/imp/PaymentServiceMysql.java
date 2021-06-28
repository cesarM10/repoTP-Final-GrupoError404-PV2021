package ar.edu.unju.fi.tpfinal.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Payment;
import ar.edu.unju.fi.tpfinal.repository.IPaymentRepository;
import ar.edu.unju.fi.tpfinal.service.IPaymentService;

@Service("paymentServiceMysql")
public class PaymentServiceMysql implements IPaymentService{

	@Autowired
	private IPaymentRepository paymentRepository; //inyecion de metodos que implementa la base de datos.
	
	/**
	 * Agrega un objeto del tipo Payment a la base de datos.
	 */
	@Override
	public void agregarPayment(Payment payment) {
		paymentRepository.save(payment);
		
	}

}

package ar.edu.unju.fi.tpfinal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PaymentsID implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "cus_customerNumber")
	private Customer customerNumber;

	@Column(name = "checkNumber")
	private String checkNumber;
	
	public PaymentsID() {
		// TODO Auto-generated constructor stub
	}

	public PaymentsID(Customer customerNumber, String checkNumber) {
		super();
		this.customerNumber = customerNumber;
		this.checkNumber = checkNumber;
	}

	public Customer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Customer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PaymentsID [customerNumber=" + customerNumber + ", checkNumber=" + checkNumber + "]";
	}
	
	
}

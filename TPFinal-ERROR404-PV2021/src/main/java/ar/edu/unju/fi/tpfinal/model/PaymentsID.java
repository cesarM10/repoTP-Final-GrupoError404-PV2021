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
	private Customer customerNumber; //atributo numero de cliente

	@Column(name = "checkNumber")
	private String checkNumber; //atributo cadena de caracteres.
	
	
	/**
	 * Constructor por defecto de PaymentsID
	 */
	public PaymentsID() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor Especializado de PaymentsID
	 * @param customerNumber
	 * @param checkNumber
	 */
	public PaymentsID(Customer customerNumber, String checkNumber) {
		super();
		this.customerNumber = customerNumber;
		this.checkNumber = checkNumber;
	}


	/**
	 * @return the customerNumber
	 */
	public Customer getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * @param customerNumber the customerNumber to set
	 */
	public void setCustomerNumber(Customer customerNumber) {
		this.customerNumber = customerNumber;
	}

	/**
	 * @return the checkNumber
	 */
	public String getCheckNumber() {
		return checkNumber;
	}

	/**
	 * @param checkNumber the checkNumber to set
	 */
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PaymentsID [customerNumber=" + customerNumber + ", checkNumber=" + checkNumber + "]";
	}
	
	
}

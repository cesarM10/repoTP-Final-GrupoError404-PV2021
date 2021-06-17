/**
 * 
 */
package ar.edu.unju.fi.tpfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Erro404
 *
 */
@Component ("customerObject")
@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cus_customer_number")	
	private Long customerNumber;
	
	@Column(name = "cus_customerName", nullable = false) 
	private String customerName;
	
	@Column(name = "cus_contactLastName", nullable = false) 
	private String contactLastName;
	
	@Column(name = "cus_contactFistrName", nullable = false) 
	private String contactFirstName;
	
	@Column(name = "cus_phone", nullable = false) 
	private String phone;
	
	@Column(name = "cus_addressLine1", nullable = false) 
	private String addressLine1;
	
	@Column(name = "cus_addressLine2") 
	private String addressLine2;
	
	@Column(name = "cus_city", nullable = false) 
	private String city;
	
	@Column(name = "cus_state") 
	private String state;
	
	@Column(name = "cus_postalCode") 
	private int postalCode;
	
	@Column(name = "cus_country", nullable = false) 
	private String country;
	
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_employee_number") 
	private Employee salesRepEmployeeNumber;//int
	
	@Column(name = "cus_creditLimit") 
	private double creditLimit;
	 
		
	/**
	 * 
	 */
	public Customer() {
		// TODO Auto-generated constructor stub
	}


	


	public Customer(Long customerNumber, String customerName, String contactLastName, String contactFirstName,
			String phone, String addressLine1, String addressLine2, String city, String state, int postalCode,
			String country, Employee salesRepEmployeeNumber, double creditLimit) {
		super();
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.contactLastName = contactLastName;
		this.contactFirstName = contactFirstName;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
		this.creditLimit = creditLimit;
	}





	public Long getCustomerNumber() {
		return customerNumber;
	}


	public void setCustomerNumber(Long customerNumber) {
		this.customerNumber = customerNumber;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getContactLastName() {
		return contactLastName;
	}


	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}





	public String getContactFirstName() {
		return contactFirstName;
	}


	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddressLine1() {
		return addressLine1;
	}


	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	public String getAddressLine2() {
		return addressLine2;
	}


	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public Employee getSalesRepEmployeeNumber() {
		return salesRepEmployeeNumber;
	}


	public void setSalesRepEmployeeNumber(Employee salesRepEmployeeNumber) {
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
	}


	public double getCreditLimit() {
		return creditLimit;
	}


	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}


	@Override
	public String toString() {
		return "Customer [customerNumber=" + customerNumber + ", customerName=" + customerName + ", contactLastName="
				+ contactLastName + ", contactFirstName=" + contactFirstName + ", phone=" + phone + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city + ", state=" + state
				+ ", postalCode=" + postalCode + ", country=" + country + ", salesRepEmployeeNumber="
				+ salesRepEmployeeNumber + ", creditLimit=" + creditLimit + "]";
	}


	
	
	
	
}

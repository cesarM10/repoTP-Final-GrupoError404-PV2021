/**
 * 
 */
package ar.edu.unju.fi.tpfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author Alvaro
 *
 */
@Component("officeObject")
@Entity
@Table(name = "offices")
public class Office {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "off_office_code")
	private Long officeCode;
	
	@Column(name = "off_city", nullable = false)
	private String city;
	
	@Column(name = "off_phone", nullable = false)
	private String phone;
	
	@Column(name = "off_addressLine1", nullable = false)
	private String addressLine1;
	
	@Column(name = "off_addressLine2")
	private String addressLine2;
	
	@Column(name = "off_state")
	private String state;
	
	@Column(name = "off_country", nullable = false)
	private String country;
	
	@Column(name = "off_postalCode", nullable = false)
	private String postalCode;
	
	@Column(name = "off_territory", nullable = false)
	private String territory;
	
	//@OneToMany(mappedBy = "office")
	//private List<Employee> employees = new ArrayList<Employee>();
	
	
	public Office() {
		// TODO Auto-generated constructor stub
	}

	public Office(Long officeCode, String city, String phone, String addressLine1, String addressLine2, String state,
			String country, String postalCode, String territory) {
		super();
		this.officeCode = officeCode;
		this.city = city;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.territory = territory;
	}

	public Long getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(Long officeCode) {
		this.officeCode = officeCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	@Override
	public String toString() {
		return "Office [officeCode=" + officeCode + ", city=" + city + ", phone=" + phone + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", state=" + state + ", country=" + country
				+ ", postalCode=" + postalCode + ", territory=" + territory + "]";
	}

	
	
}

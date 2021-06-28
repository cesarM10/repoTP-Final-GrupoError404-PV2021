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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alvaro
 *
 */
@Component("employeeObject")
@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_employee_number")
	private Long employeeNumber; //clave primaria
	
	@NotEmpty(message = "El campo no debe estar vacio.")
	@Size(min = 3, max = 150,  message = "El campo 'Apellido' debe tener como minimo 3 caracteres.")
	@Column(name = "emp_lastName", nullable = false)
	private String lastName; //atributo apellido
	
	@NotEmpty(message = "El campo no debe estar vacio.")
	@Size(min = 3, max = 150,  message = "El campo 'Nombre' debe tener como minimo 3 caracteres.")
	@Column(name = "emp_firstName", nullable = false)
	private String firstName; //atributo nombre
	
	@NotEmpty(message = "El campo no debe estar vacio.")
	@Size(min = 3, max = 150,  message = "El campo 'Extension' debe tener como minimo 3 caracteres.")
	@Column(name = "emp_extension", nullable = false)
	private String extension; //atributo extension podria ser un domicilio
	
	@Email(message = "Ingrese un formato de email valido.")
	@Column(name = "emp_email", nullable = false)
	private String email; //atributo e-mail o correo electronico
	
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_reportsTo")
	private Employee reportsTo; //atributo encargado de este empleado
	
	@NotEmpty(message = "El campo no debe estar vacio.")
	@Size(min = 3, max = 150,  message = "El campo 'Titulo' debe tener como minimo 3 caracteres.")
	@Column(name = "emp_jobTitle", nullable = false)
	private String jobTitle; //atributo titulo o cargo que desempeña en la empresa
	
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "off_office_code") //relacion a una office en la base de datos 
	private Office office; //atributo oficina 
	
	/**
	 * Constructor por defecto de Employee
	 */
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor Especializado de Employee
	 * @param employeeNumber
	 * @param lastName
	 * @param firstName
	 * @param extension
	 * @param email
	 * @param reportsTo
	 * @param jobTitle
	 * @param office
	 */
	public Employee(Long employeeNumber, String lastName, String firstName, String extension, String email,
			Employee reportsTo, String jobTitle, Office office) {
		super();
		this.employeeNumber = employeeNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.extension = extension;
		this.email = email;
		this.reportsTo = reportsTo;
		this.jobTitle = jobTitle;
		this.office = office;
	}

	

	/**
	 * @return the employeeNumber
	 */
	public Long getEmployeeNumber() {
		return employeeNumber;
	}

	/**
	 * @param employeeNumber the employeeNumber to set
	 */
	public void setEmployeeNumber(Long employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the reportsTo
	 */
	public Employee getReportsTo() {
		return reportsTo;
	}

	/**
	 * @param reportsTo the reportsTo to set
	 */
	public void setReportsTo(Employee reportsTo) {
		this.reportsTo = reportsTo;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return the office
	 */
	public Office getOffice() {
		return office;
	}

	/**
	 * @param office the office to set
	 */
	public void setOffice(Office office) {
		this.office = office;
	}

	@Override
	public String toString() {
		return "Employee [employeeNumber=" + employeeNumber + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", extension=" + extension + ", email=" + email + ", reportsTo=" + reportsTo + ", jobTitle="
				+ jobTitle + ", office=" + office + "]";
	}

		
	
}

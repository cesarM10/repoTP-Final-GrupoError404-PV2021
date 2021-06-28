package ar.edu.unju.fi.tpfinal.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component ( "orderObject" )
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ord_order_number")
	private Long orderNumber;
	
	//@NotNull(message = "El campo 'Fecha de Orden' no debe ser nulo.")
	@Column(name = "ord_orderDate", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate orderDate;
	
	//@NotNull(message = "El campo 'Fecha de Pedido' no debe ser nulo.")
	@Column(name = "ord_requiredDate", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate requiredDate;
	
	@NotNull(message = "El campo 'Fecha de Entrega' no debe ser nulo.")
	@Column(name = "ord_shippedDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate shippedDate;
	
	@NotEmpty(message="Seleccione una opcion.")
	@Column(name = "ord_status", nullable = false)
	private String status;
	
	@NotEmpty(message = "El campo no debe estar vacio.")
	@Size(min = 5, max = 150,  message = "El campo 'Comentario' debe tener como minimo 5 caracteres.")
	@Column(name = "ord_comments")
	private String comments;
	
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cus_customer_number")
	private Customer customer;
	
		
	/**
	 * 
	 */
	public Order() {
		// TODO Auto-generated constructor stub
	}


	public Order(Long orderNumber, LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, String status,
			String comments, Customer customer) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.status = status;
		this.comments = comments;
		this.customer = customer;
	}


	public Long getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}


	public LocalDate getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}


	public LocalDate getRequiredDate() {
		return requiredDate;
	}


	public void setRequiredDate(LocalDate requiredDate) {
		this.requiredDate = requiredDate;
	}


	public LocalDate getShippedDate() {
		return shippedDate;
	}


	public void setShippedDate(LocalDate shippedDate) {
		this.shippedDate = shippedDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", requiredDate=" + requiredDate
				+ ", shippedDate=" + shippedDate + ", status=" + status + ", comments=" + comments + ", customer="
				+ customer + "]";
	}
	

}

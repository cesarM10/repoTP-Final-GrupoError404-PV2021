package ar.edu.unju.fi.tpfinal.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OrderDetailsID implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "ord_order_number")
	private Order orderNumber; //atributo numero de order del tipo objeto Order
	
	@ManyToOne
	@JoinColumn(name = "pro_product_code")
	private Product producCode; //atributo codigo de producto del tipo objeto Product
	
	/**
	 * Constructor por defecto de OrderDetailID
	 */
	public OrderDetailsID() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor Especializado de OrderDetailID
	 * @param orderNumber
	 * @param producCode
	 */
	public OrderDetailsID(Order orderNumber, Product producCode) {
		super();
		this.orderNumber = orderNumber;
		this.producCode = producCode;
	}


	/**
	 * @return the orderNumber
	 */
	public Order getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(Order orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the producCode
	 */
	public Product getProducCode() {
		return producCode;
	}

	/**
	 * @param producCode the producCode to set
	 */
	public void setProducCode(Product producCode) {
		this.producCode = producCode;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OrderDetailsID [orderNumber=" + orderNumber + ", producCode=" + producCode + "]";
	}
	
	
	
}

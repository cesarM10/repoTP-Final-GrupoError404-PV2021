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
	private Order orderNumber;
	
	@ManyToOne
	@JoinColumn(name = "pro_product_code")
	private Product producCode;
	
	public OrderDetailsID() {
		// TODO Auto-generated constructor stub
	}

	public OrderDetailsID(Order orderNumber, Product producCode) {
		super();
		this.orderNumber = orderNumber;
		this.producCode = producCode;
	}

	public Order getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Order orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Product getProducCode() {
		return producCode;
	}

	public void setProducCode(Product producCode) {
		this.producCode = producCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OrderDetailsID [orderNumber=" + orderNumber + ", producCode=" + producCode + "]";
	}
	
	
	
}

/**
 * 
 */
package ar.edu.unju.fi.tpfinal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
@Entity
@Table(name = "order_details")
public class OrderDetail implements Serializable{  //410
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OrderDetailsID id;
	
	@Column(name = "ord_quantityOrdered")
	private   int quantityOrdered;
	
	@Column(name = "ord_priceEach")
	private   double priceEach;
	
	@Column(name = "ord_order_line_number")
	private   int orderLineNumber;
	
	public OrderDetail() {
		// TODO Auto-generated constructor stub
	}

	public OrderDetail(OrderDetailsID id, int quantityOrdered, double priceEach, int orderLineNumber) {
		super();
		this.id = id;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
	}

	public OrderDetailsID getId() {
		return id;
	}

	public void setId(OrderDetailsID id) {
		this.id = id;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public double getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(double priceEach) {
		this.priceEach = priceEach;
	}

	public int getOrderLineNumber() {
		return orderLineNumber;
	}

	public void setOrderLineNumber(int orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public double getSubTotal() {
		double subTotal = 0;
		
		subTotal = this.getPriceEach() * this.getQuantityOrdered();
		
		return subTotal;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", quantityOrdered=" + quantityOrdered + ", priceEach=" + priceEach
				+ ", orderLineNumber=" + orderLineNumber + "]";
	}
	

}

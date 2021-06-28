/**
 * 
 */
package ar.edu.unju.fi.tpfinal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

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
	private OrderDetailsID id; //clave primaria combinada con producto y order en el objeto OrderDetailsID
	
	@Min(value = 1, message = "El campo 'Cantidad' no debe ser negativo ni cero.")
	@Column(name = "ord_quantityOrdered")
	private int quantityOrdered; //atributo cantidad ordenada de un producto
	
	//@Min(value = 1, message = "El campo 'Precio Unitario' no debe ser negativo ni cero.")
	@Column(name = "ord_priceEach")
	private double priceEach; //atributo precio unitario del producto
	
	//@Min(value = 1, message = "El campo 'Numero de Orden' no debe ser negativo ni cero.")
	@Column(name = "ord_order_line_number")
	private int orderLineNumber; //atributo numeracion de una order
	
	/**
	 * Constructor por defecto de OrderDetail
	 */
	public OrderDetail() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor Especializado de OrderDetail
	 * @param id
	 * @param quantityOrdered
	 * @param priceEach
	 * @param orderLineNumber
	 */
	public OrderDetail(OrderDetailsID id, int quantityOrdered, double priceEach, int orderLineNumber) {
		super();
		this.id = id;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
	}

	
	
	/**
	 * @return the id
	 */
	public OrderDetailsID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(OrderDetailsID id) {
		this.id = id;
	}

	/**
	 * @return the quantityOrdered
	 */
	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	/**
	 * @param quantityOrdered the quantityOrdered to set
	 */
	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	/**
	 * @return the priceEach
	 */
	public double getPriceEach() {
		return priceEach;
	}

	/**
	 * @param priceEach the priceEach to set
	 */
	public void setPriceEach(double priceEach) {
		this.priceEach = priceEach;
	}

	/**
	 * @return the orderLineNumber
	 */
	public int getOrderLineNumber() {
		return orderLineNumber;
	}

	/**
	 * @param orderLineNumber the orderLineNumber to set
	 */
	public void setOrderLineNumber(int orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Metodo que realiza el calculo de subtotal conformado
	 * por la cantidad de producto y su precio unitario
	 * @return subTotal
	 */
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

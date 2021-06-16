package ar.edu.unju.fi.tpfinal.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
@Component ( "paymentObject" )
@Entity
@Table(name = "payments")
public class Payment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PaymentsID id ;
	
	@Column(name = "pay_paymentDate", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate paymentDate;
	
	@Column(name = "pay_amount", nullable = false)
	private double amount;
	
	
	
	/**
	 * 
	 */
	public Payment() { 
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param paymentDate
	 * @param amount
	 */
	public Payment(LocalDate paymentDate, double amount) {
		super();
		this.paymentDate = paymentDate;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Payment [paymentDate=" + paymentDate + ", amount=" + amount + "]";
	}
	/**
	 * @return the paymentDate
	 */
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}

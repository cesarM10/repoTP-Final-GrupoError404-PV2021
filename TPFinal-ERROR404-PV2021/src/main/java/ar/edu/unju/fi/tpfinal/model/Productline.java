/**
 * 
 */
package ar.edu.unju.fi.tpfinal.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author Alvaro
 *
 */
@Component
@Entity
@Table(name = "productlines")
public class Productline {  //7658
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pli_product_line")
	private  Long productLine;
	
	@Column(name = "pli_textDescription")
	private  String textDescription;
	
	@Column(name = "pli_htmlDescription")
	private  String htmlDescription;
	
	@Column(name = "pli_image")
	private  byte[] image;//mediumblob
	
	@OneToMany(mappedBy ="productline", cascade= CascadeType.ALL, orphanRemoval=true)
	private List <Product> products = new ArrayList<Product>();
	
	
	public Productline() {
		// TODO Auto-generated constructor stub
	}


	public Productline(Long productLine, String textDescription, String htmlDescription, byte[] image) {
		super();
		this.productLine = productLine;
		this.textDescription = textDescription;
		this.htmlDescription = htmlDescription;
		this.image = image;
	}


	public Long getProductLine() {
		return productLine;
	}


	public void setProductLine(Long productLine) {
		this.productLine = productLine;
	}


	public String getTextDescription() {
		return textDescription;
	}


	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}


	public String getHtmlDescription() {
		return htmlDescription;
	}


	public void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	@Override
	public String toString() {
		return "Productline [productLine=" + productLine + ", textDescription=" + textDescription + ", htmlDescription="
				+ htmlDescription + ", image=" + Arrays.toString(image) + "]";
	}
	
	
	
}

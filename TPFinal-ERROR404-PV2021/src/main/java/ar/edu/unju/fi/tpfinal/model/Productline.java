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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
	
	@NotEmpty(message = "El campo no debe estar vacio.")
	@Size(min = 3, max = 150,  message = "El campo de la Descripcion debe tener como minimo 3 caracteres.")
	@Column(name = "pli_textDescription")
	private  String textDescription;
	
	@NotEmpty(message = "El campo no debe estar vacio.")
	@Size(min = 30, max = 150,  message = "El campo del HTML debe tener como minimo 30 caracteres.")
	@Column(name = "pli_htmlDescription")
	private  String htmlDescription;
	
	/*@NotEmpty(message = "El campo no debe estar vacio.") 
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")*/
    //@Column(name = "pli_image")
	//private  byte[] image;//mediumblob
	private  String image;
	
	@OneToMany(mappedBy ="productline", cascade= CascadeType.ALL, orphanRemoval=true)//controlar
	private List <Product> products = new ArrayList<Product>();//controlar
	
	
	public Productline() {
		// TODO Auto-generated constructor stub
	}


	public Productline(Long productLine, String textDescription, String htmlDescription, String image) {
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


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	@Override
	public String toString() {
		return "Productline [productLine=" + productLine + ", textDescription=" + textDescription + ", htmlDescription="
				+ htmlDescription + ", image=" + "]";//Arrays.toString(image) + "]";
	}
	
	
	
}

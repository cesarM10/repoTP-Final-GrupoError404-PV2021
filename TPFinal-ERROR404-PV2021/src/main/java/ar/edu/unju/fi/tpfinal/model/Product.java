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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alvaro
 *
 */
@Component ( "productObject" )
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_product_code")
	private Long productCode;
	
	@Column(name = "pro_productName", nullable = false)
	private String productName;
	
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pli_product_line")
	private Productline productline;
	
	@Column(name = "pro_productScale", nullable = false)
	private String productScale;
	
	@Column(name = "pro_productVendor", nullable = false)
	private String productVendor;
	
	@Column(name = "pro_productDescription", nullable = false)
	private String productDescription;
	
	@Column(name = "pro_quantityInStock", nullable = false)
	private int quantityInStock;
	
	@Column(name = "pro_buyPrice", nullable = false)
	private double buyPrice;
	
	@Column(name = "pro_MSRP", nullable = false)
	private double MSRP;
	
	
	public Product() {
		// TODO Auto-generated constructor stub
	}


	public Product(Long productCode, String productName, Productline productline, String productScale,
			String productVendor, String productDescription, int quantityInStock, double buyPrice, double mSRP) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productline = productline;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.productDescription = productDescription;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		MSRP = mSRP;
	}


	public Long getProductCode() {
		return productCode;
	}


	public void setProductCode(Long productCode) {
		this.productCode = productCode;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Productline getProductline() {
		return productline;
	}


	public void setProductline(Productline productline) {
		this.productline = productline;
	}


	public String getProductScale() {
		return productScale;
	}


	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}


	public String getProductVendor() {
		return productVendor;
	}


	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public int getQuantityInStock() {
		return quantityInStock;
	}


	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}


	public double getBuyPrice() {
		return buyPrice;
	}


	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}


	public double getMSRP() {
		return MSRP;
	}


	public void setMSRP(double mSRP) {
		MSRP = mSRP;
	}


	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", productName=" + productName + ", productline=" + productline
				+ ", productScale=" + productScale + ", productVendor=" + productVendor + ", productDescription="
				+ productDescription + ", quantityInStock=" + quantityInStock + ", buyPrice=" + buyPrice + ", MSRP="
				+ MSRP + "]";
	}


	
	
}

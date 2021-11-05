package com.biztantu.api.lead;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.biztantu.api.product.Product;

@Entity
@Table(name="LEAD")
public class Lead {
	
	@Id
    @GeneratedValue
	private Long leadId;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
        name = "LeadProduct", 
        joinColumns = { @JoinColumn(name = "leadId") }, 
        inverseJoinColumns = { @JoinColumn(name = "productId") }
    )
	private Set<Product> product;

	public Long getLeadId() {
		return leadId;
	}

	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

}

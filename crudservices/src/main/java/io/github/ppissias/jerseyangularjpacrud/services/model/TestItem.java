package io.github.ppissias.jerseyangularjpacrud.services.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TestItem {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	private String transactionDate;
	private String descrpition;
	private String detailedDescription; 
	private Double amount;
	private String category;
	
	public TestItem(String transactionDate, String descrpition, String detailedDescription, Double amount, String category) {
		super();
		this.transactionDate = transactionDate;
		this.descrpition = descrpition;
		this.detailedDescription = detailedDescription;
		this.amount = amount;
		this.category = category;
	}
	
	public TestItem() {
		
	}
	
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getDescrpition() {
		return descrpition;
	}
	public void setDescrpition(String descrpition) {
		this.descrpition = descrpition;
	}
	public String getDetailedDescription() {
		return detailedDescription;
	}
	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ExpenseEntry [id=" + id + ", transactionDate=" + transactionDate + ", descrpition=" + descrpition
				+ ", detailedDescription=" + detailedDescription + ", amount=" + amount + ", category=" + category
				+ "]";
	} 

	
}

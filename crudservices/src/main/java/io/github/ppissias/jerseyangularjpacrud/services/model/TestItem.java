package io.github.ppissias.jerseyangularjpacrud.services.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This is the main simple data resource for this application
 * It consists of an integer ID and a String description.
 * 
 * @author Petros Pissias
 *
 */
@Entity
public class TestItem {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	private String descrpition;
	
	public TestItem(String descrpition) {
		super();
		this.descrpition = descrpition;

	}
	
	public TestItem() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescrpition() {
		return descrpition;
	}

	public void setDescrpition(String descrpition) {
		this.descrpition = descrpition;
	}

	@Override
	public String toString() {
		return "TestItem [id=" + id + ", descrpition=" + descrpition + "]";
	}

	
}

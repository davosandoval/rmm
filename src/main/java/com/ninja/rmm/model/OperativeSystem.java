package com.ninja.rmm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="operative_system")
public class OperativeSystem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6231771253477798441L;
	
	@Id
	private String id;
	
	@NotBlank
	private String description;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}

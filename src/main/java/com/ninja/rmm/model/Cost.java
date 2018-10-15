/**
 * 
 */
package com.ninja.rmm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author dsandoval
 *
 */
@Entity
@Table(name="service_cost")
public class Cost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6048055254526031737L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	
	@NotBlank
	private Integer cost;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}

}

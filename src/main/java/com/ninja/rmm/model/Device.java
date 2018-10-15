/**
 * 
 */
package com.ninja.rmm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author dsandoval
 *
 */
@Entity
@Table(name="device")
public class Device implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8876269229081010155L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_operative_system_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private OperativeSystem operativeSystem;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_device_customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private Customer customer;
	
	
	public Device() {
		this.customer = new Customer();
		this.operativeSystem = new OperativeSystem();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OperativeSystem getOperativeSystem() {
		return operativeSystem;
	}

	public void setOperativeSystem(OperativeSystem operativeSystem) {
		this.operativeSystem = operativeSystem;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



}

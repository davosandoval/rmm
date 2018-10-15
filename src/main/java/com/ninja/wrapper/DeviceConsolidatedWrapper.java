package com.ninja.wrapper;

import java.util.List;

import com.ninja.rmm.model.ServiceCustomer;

public class DeviceConsolidatedWrapper {
	private String user;
	private List<OperativeSystemConsolidatedWrapper> operativeSystemListConsolidated;
	private List<ServiceCustomer> servicesList;
	private List<CostWrapper> explanationCost;
	private Integer totalCost;
	
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public List<OperativeSystemConsolidatedWrapper> getOperativeSystemListConsolidated() {
		return operativeSystemListConsolidated;
	}
	public void setOperativeSystemListConsolidated(
			List<OperativeSystemConsolidatedWrapper> operativeSystemListConsolidated) {
		this.operativeSystemListConsolidated = operativeSystemListConsolidated;
	}
	public List<ServiceCustomer> getServicesList() {
		return servicesList;
	}
	public void setServicesList(List<ServiceCustomer> servicesList) {
		this.servicesList = servicesList;
	}
	public List<CostWrapper> getExplanationCost() {
		return explanationCost;
	}
	public void setExplanationCost(List<CostWrapper> explanationCost) {
		this.explanationCost = explanationCost;
	}
	public Integer getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}
	
	
}

package com.ninja.rmm.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninja.rmm.model.Cost;
import com.ninja.rmm.model.Customer;
import com.ninja.rmm.model.Device;
import com.ninja.rmm.model.ServiceCustomer;
import com.ninja.rmm.repository.CostRepository;
import com.ninja.rmm.repository.DeviceRepository;
import com.ninja.rmm.repository.ServiceCustomerRepository;
import com.ninja.wrapper.CostWrapper;
import com.ninja.wrapper.DeviceConsolidatedWrapper;
import com.ninja.wrapper.OperativeSystemConsolidatedWrapper;

enum SmartService{
	ANTIVIRUS, CLOUDBERRY, PSA, TEAMVIEWER, REGISTERED_DEVICE
}

enum OS{
	MAC, WIN_SERVER, WIN_WORKSTATION
}

enum Antivirus{
	MAC_ANTIVIRUS, WIN_ANTIVIRUS
}

@RestController
@RequestMapping("/rmm")
public class ConsolidatedController extends BaseController {
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private ServiceCustomerRepository serviceCustomerRepository;
	
	@Autowired
	private CostRepository costRepository;
	
	@GetMapping("/consolidated/{user}")
	public DeviceConsolidatedWrapper getResumeByUser(@PathVariable(value = "user")String user){
		Customer customer = getCustomerByUser(user);
		
		List<Device> deviceList = deviceRepository.findByUser(customer.getId());
		List<ServiceCustomer> serviceCustomerList = serviceCustomerRepository.findByUser(customer.getId());
		List<Cost> costList = costRepository.findAll();
		
		
		deviceList.sort(Comparator.comparing(Device::getOperativeSystem, (os1, os2) ->{
			if(os1.getId().equals(os2.getId())) {
				return 0;
			}
			
			if(os1.getId().compareTo(os2.getId())<0) {
				return -1;
			}else {
				return 1;
			}
		}));
		List<OperativeSystemConsolidatedWrapper> osConsolidatedList = new ArrayList<OperativeSystemConsolidatedWrapper>();
		int i=0;
		int sum = 0;
		int totalDevices = 0;
		int totalMac = 0;
		for(Device device:deviceList) {
			if(i==0 || !osConsolidatedList.get(i-1).getOperativeSystemId().equals(device.getOperativeSystem().getId())) {
				sum = 1;
				OperativeSystemConsolidatedWrapper osSystemConsolidatedWrapper = new OperativeSystemConsolidatedWrapper();
				osSystemConsolidatedWrapper.setTotal(sum);
				osSystemConsolidatedWrapper.setOperativeSystemId(device.getOperativeSystem().getId());
				osConsolidatedList.add(osSystemConsolidatedWrapper);
				i++;
			}else if(osConsolidatedList.get(i-1).getOperativeSystemId().equals(device.getOperativeSystem().getId())) {
				osConsolidatedList.get(i-1).setTotal(++sum);
			}
			
			if(OS.MAC.toString().equals(device.getOperativeSystem().getId())) {
				totalMac++;
			}
			totalDevices++;
			
		}
		int totalWin = totalDevices - totalMac;
		
		List<CostWrapper> costWrapperList = new ArrayList<CostWrapper>();
		CostWrapper deviceCostWrapper = new CostWrapper();
		deviceCostWrapper.setFeature(SmartService.REGISTERED_DEVICE.toString());
		deviceCostWrapper.setCost(getCostByFeature(costList, SmartService.REGISTERED_DEVICE.toString()) * totalDevices);
		costWrapperList.add(deviceCostWrapper);
		
		int totalCost = deviceCostWrapper.getCost();
		for(ServiceCustomer serviceCustomer:serviceCustomerList) {
			CostWrapper costWrapper = new CostWrapper();
			costWrapper.setFeature(serviceCustomer.getService().getId());
			if(SmartService.ANTIVIRUS.toString().equals(serviceCustomer.getService().getId())) {
				costWrapper.setCost(getCostByFeature(costList, Antivirus.MAC_ANTIVIRUS.toString()) * totalMac);
				costWrapper.setCost(costWrapper.getCost() + (getCostByFeature(costList, Antivirus.WIN_ANTIVIRUS.toString()) * totalWin));
			}else {
				costWrapper.setCost(getCostByFeature(costList, serviceCustomer.getService().getId()) * totalDevices);
			}
			totalCost+=costWrapper.getCost();
			costWrapperList.add(costWrapper);
		}
		
		DeviceConsolidatedWrapper consolidatedData = new DeviceConsolidatedWrapper();
		consolidatedData.setUser(user);
		consolidatedData.setOperativeSystemListConsolidated(osConsolidatedList);
		consolidatedData.setServicesList(serviceCustomerList);
		consolidatedData.setExplanationCost(costWrapperList);
		consolidatedData.setTotalCost(totalCost);
		return consolidatedData;
	}
	
	private Integer getCostByFeature(List<Cost> costList, String feature) {
		Integer value = 0;
		for(Cost cost:costList) {
			if(feature.indexOf(cost.getId()) != -1) {
				value = cost.getCost();
			}
		}
		return value;
	}

}

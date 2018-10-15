/**
 * 
 */
package com.ninja.rmm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninja.rmm.exception.DuplicatedResourceFoundException;
import com.ninja.rmm.exception.ResourceNotFoundException;
import com.ninja.rmm.model.Customer;
import com.ninja.rmm.model.Device;
import com.ninja.rmm.model.OperativeSystem;
import com.ninja.rmm.repository.DeviceRepository;
import com.ninja.rmm.repository.OperativeSystemRepository;
import com.ninja.wrapper.DeviceSuccessfulWrapper;
import com.ninja.wrapper.DeviceWrapper;
import com.ninja.wrapper.SuccessfulWrapper;

/**
 * @author dsandoval
 *
 */
@RestController
@RequestMapping("/rmm")
public class DeviceController extends BaseController{
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private OperativeSystemRepository operativeSystemRepository;
	
	
	@GetMapping("/deviceCustomer/{user}")
	public List<Device> getDevicesByUser(@PathVariable(value = "user")String user){
		Customer customer = getCustomerByUser(user);
		
		List<Device> deviceList = deviceRepository.findByUser(customer.getId());
		return deviceList;
	}
	
	@PostMapping("/deviceCustomer/{user}")
	public ResponseEntity<DeviceSuccessfulWrapper> saveDeviceByUser(@PathVariable(value = "user")String user, @Valid @RequestBody DeviceWrapper deviceWrapper){
		return modifyOrSaveDevice(user, deviceWrapper, false);
	}
	
	@DeleteMapping("/deviceCustomer/{user}/{name}")
	public ResponseEntity<SuccessfulWrapper> deleteDeviceByNameAndUser(@PathVariable(value = "user")String user, @PathVariable(value = "name")String name){
		Customer customer = getCustomerByUser(user);
		
		Device device = deviceRepository.findByUserAndName(customer.getId(), name);
		if(device == null) {
			throw new ResourceNotFoundException("deviceCustomer", "operativeSystemName", name);
		}
		deviceRepository.delete(device);
		return new ResponseEntity<SuccessfulWrapper>(new SuccessfulWrapper("Device deleted successful"), HttpStatus.OK);
	}
	
	@PutMapping("/deviceCustomer/{user}/{oldName}")
	public ResponseEntity<DeviceSuccessfulWrapper> modifyDeviceByUser(@PathVariable(value = "user")String user, @PathVariable(value = "oldName")String oldName, @Valid @RequestBody DeviceWrapper deviceWrapper){
		deviceWrapper.setOldName(oldName);
		return modifyOrSaveDevice(user, deviceWrapper, true);
	}
	
	private ResponseEntity<DeviceSuccessfulWrapper> modifyOrSaveDevice(String user, DeviceWrapper deviceWrapper, Boolean isUpdate) {
		Customer customer = getCustomerByUser(user);
		
		OperativeSystem operativeSystem = operativeSystemRepository.findById(deviceWrapper.getOsId())
				.orElseThrow(() -> new ResourceNotFoundException("deviceCustomer", "operativeSystemId", deviceWrapper.getOsId()));
		Device device;
		if(isUpdate) {
			device = deviceRepository.findByUserAndName(customer.getId(), deviceWrapper.getOldName());
			if(device == null) {
				throw new ResourceNotFoundException("deviceCustomer", "deviceName", deviceWrapper.getOldName());
			}
		}else {
			device = new Device();
			device.getCustomer().setId(customer.getId());
		}

		device.setName(deviceWrapper.getName());
		device.setOperativeSystem(operativeSystem);
	
		try {
			return new ResponseEntity<DeviceSuccessfulWrapper>(
					new DeviceSuccessfulWrapper(deviceRepository.save(device), isUpdate?"Device updated successfully":"Device inserted successfully"), HttpStatus.OK);
		}catch(Exception e) {
			throw new DuplicatedResourceFoundException("deviceCustomer", "deviceName", deviceWrapper.getName());
		}
	}

}

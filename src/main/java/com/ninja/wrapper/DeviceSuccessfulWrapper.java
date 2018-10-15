/**
 * 
 */
package com.ninja.wrapper;

import com.ninja.rmm.model.Device;

/**
 * @author dsandoval
 *
 */
public class DeviceSuccessfulWrapper extends SuccessfulWrapper {
	private Device device;
	
	public DeviceSuccessfulWrapper(Device device, String message) {
		super(message);
		this.device = device;
	}

	private DeviceSuccessfulWrapper(String message) {
		super(message);
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

}

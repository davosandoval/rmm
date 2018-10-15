package com.ninja.wrapper;

import java.io.Serializable;

public class DeviceWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4203917364037247570L;
	
	private String name;
	private String osId;
	
	private String oldName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOsId() {
		return osId;
	}
	public void setOsId(String osId) {
		this.osId = osId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

}

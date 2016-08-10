/** 
* Copyright @2012-2016 Busap.com All Rights Reserved. 
*/ 
package com.busap.cctv.baidudata.docking.domain; 

import javax.persistence.Entity;

/**
 *  
 *
 * @author liweizhi
 * @since 2014年11月17日
 * @description: 终端型号模型
 */

@Entity
public class TerminalModel extends BasePersistable<Long> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2358980859324278441L;

	
	/** 型号名称. */
	private String name;
	
	/** 设备厂商. */
	private String provider;
	
	/** 操作系统. */
	private String os;
	
	/** 硬盘大小. */
	private String disk;
	
	/** cpu 信息. */
	private String cpu;
	
	/** 内存大小. */
	private String memory;

	/**
	 * Gets the 型号名称.
	 * 
	 * @return the 型号名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the 型号名称.
	 * 
	 * @param name
	 *            the new 型号名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the 设备厂商.
	 * 
	 * @return the 设备厂商
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * Sets the 设备厂商.
	 * 
	 * @param provider
	 *            the new 设备厂商
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * Gets the 操作系统.
	 * 
	 * @return the 操作系统
	 */
	public String getOs() {
		return os;
	}

	/**
	 * Sets the 操作系统.
	 * 
	 * @param os
	 *            the new 操作系统
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * Gets the 硬盘大小.
	 * 
	 * @return the 硬盘大小
	 */
	public String getDisk() {
		return disk;
	}

	/**
	 * Sets the 硬盘大小.
	 * 
	 * @param disk
	 *            the new 硬盘大小
	 */
	public void setDisk(String disk) {
		this.disk = disk;
	}

	/**
	 * Gets the cpu 信息.
	 * 
	 * @return the cpu 信息
	 */
	public String getCpu() {
		return cpu;
	}

	/**
	 * Sets the cpu 信息.
	 * 
	 * @param cpu
	 *            the new cpu 信息
	 */
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	/**
	 * Gets the 内存大小.
	 * 
	 * @return the 内存大小
	 */
	public String getMemory() {
		return memory;
	}

	/**
	 * Sets the 内存大小.
	 * 
	 * @param memory
	 *            the new 内存大小
	 */
	public void setMemory(String memory) {
		this.memory = memory;
	}
	
	
}
          

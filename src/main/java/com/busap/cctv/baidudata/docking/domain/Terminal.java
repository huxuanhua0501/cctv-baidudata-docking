/** 
* Copyright @2012-2016 Busap.com All Rights Reserved. 
*/ 
package com.busap.cctv.baidudata.docking.domain; 

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class Terminal.
 * 
 * @author liweizhi
 * @since 2014年11月7日
 * @description: 设备基类 StationTerminal与BusTerminal继承他
 */
@Entity  
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)  
@DiscriminatorColumn(name="terminal_type",discriminatorType=DiscriminatorType.STRING)  
public class Terminal extends BasePersistable<Long> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1392143447459935000L;

	/**
	 * 
	 * 设备类型.
	 */
	public enum TerminalType {
		
		/** 基站设备. */
		Station((short)1, "基站设备"),
		
		/** 公交设备. */
		Bus((short)2, "公交设备");
		
		/**
		 * Instantiates a new terminal type.
		 * 
		 * @param type
		 *            the type
		 * @param meaning
		 *            the meaning
		 */
		private TerminalType(short type, String meaning){
			this.type = type;
			this.meaning = meaning;
		}
		
		/** The type. */
		private short type;
		
		/** The meaning. */
		private String meaning;
		
		/**
		 * 获取类型代码.
		 * 
		 * @return the type
		 */
		public short getType(){
			return this.type;
		}
		
		/**
		 * 获取类型含义.
		 * 
		 * @return the meaning
		 */
		public String getMeaning(){
			return this.meaning;
		}
		
		/**
		 * 根据类型代码获取含义.
		 * 
		 * @param type
		 *            the type
		 * @return the meaning by type
		 */
		public static String getMeaningByType(final short type){
			for (TerminalType terminalType : TerminalType.values()) {
				if (terminalType.getType() == type){
					return terminalType.getMeaning();
				}
			}
			return "未知";
		}

	}
	
	/** 终端编号. */
	private String terminalNo;
	
	/** mac地址. */
	private String macAddress;
	
	/** 设备编码. */
	@Column(unique = true)
	private String deviceCode;
	
	/** 终端key. */
	@Column(unique = true)
	private String appKey;
	
	/** 终端secret. */
	private String appSecret;
	
	/** The wifi名称. */
	private String wifi;
	
	/** wifi密码. */
	private String password;
	
	/** 校验码. */
	private String checkCode;

	/** 激活时间. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date activeTime;
	
	/** The terminal model. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="model_id")
	private TerminalModel terminalModel;
	
	
	/** 人工干预的调节排序 */
	@Column(nullable=true)
	private short adjustedSort;
	
	/** 智能评分取得的排序 */
	@Column(nullable=true)
	private short intelliSort;
	/**
	 * Gets the 终端编号.
	 * 
	 * @return the 终端编号
	 */
	public String getTerminalNo() {
		return terminalNo;
	}

	/**
	 * Sets the 终端编号.
	 * 
	 * @param terminalNo
	 *            the new 终端编号
	 */
	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	/**
	 * Gets the mac地址.
	 * 
	 * @return the mac地址
	 */
	public String getMacAddress() {
		return macAddress;
	}

	/**
	 * Sets the mac地址.
	 * 
	 * @param macAddress
	 *            the new mac地址
	 */
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	/**
	 * Gets the 设备编码.
	 * 
	 * @return the 设备编码
	 */
	public String getDeviceCode() {
		return deviceCode;
	}

	/**
	 * Sets the 设备编码.
	 * 
	 * @param deviceCode
	 *            the new 设备编码
	 */
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	/**
	 * Gets the 终端key.
	 * 
	 * @return the 终端key
	 */
	public String getAppKey() {
		return appKey;
	}

	/**
	 * Sets the 终端key.
	 * 
	 * @param appKey
	 *            the new 终端key
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**
	 * Gets the 终端secret.
	 * 
	 * @return the 终端secret
	 */
	public String getAppSecret() {
		return appSecret;
	}

	/**
	 * Sets the 终端secret.
	 * 
	 * @param appSecret
	 *            the new 终端secret
	 */
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	/**
	 * Gets the wifi名称.
	 * 
	 * @return the wifi名称
	 */
	public String getWifi() {
		return wifi;
	}

	/**
	 * Sets the wifi名称.
	 * 
	 * @param wifi
	 *            the new wifi名称
	 */
	public void setWifi(String wifi) {
		this.wifi = wifi;
	}

	/**
	 * Gets the wifi密码.
	 * 
	 * @return the wifi密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the wifi密码.
	 * 
	 * @param password
	 *            the new wifi密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the 激活时间.
	 * 
	 * @return the 激活时间
	 */
	public Date getActiveTime() {
		return activeTime;
	}

	/**
	 * Sets the 激活时间.
	 * 
	 * @param activeTime
	 *            the new 激活时间
	 */
	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	/**
	 * 获取当前设备所属类型.
	 * 
	 * @return the terminal type
	 */
	public Short getTerminalType(){
		if (this instanceof StationTerminal) {
			return TerminalType.Station.getType();
		}else {
			return TerminalType.Bus.getType();
		}
	}
	
	/**
	 * 是否是车载终端.
	 * 
	 * @return true：是，false:不是
	 */
	public boolean isBusTerminal(){
		return this instanceof BusTerminal;
	}
	
	/**
	 * 是否是基站终端.
	 * 
	 * @return true：是，false:不是
	 */
	public boolean isStationTerminal(){
		return this instanceof StationTerminal;
	}

	/**
	 * Gets the terminal model.
	 * 
	 * @return the terminal model
	 */
	public TerminalModel getTerminalModel() {
		return terminalModel;
	}

	/**
	 * Sets the terminal model.
	 * 
	 * @param terminalModel
	 *            the new terminal model
	 */
	public void setTerminalModel(TerminalModel terminalModel) {
		this.terminalModel = terminalModel;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public short getAdjustedSort() {
		return adjustedSort;
	}

	public void setAdjustedSort(short adjustedSort) {
		this.adjustedSort = adjustedSort;
	}

	public short getIntelliSort() {
		return intelliSort;
	}

	public void setIntelliSort(short intelliSort) {
		this.intelliSort = intelliSort;
	}
	
	
}
          

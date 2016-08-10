package com.busap.cctv.baidudata.docking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * SIM 卡模型
 * 
 * @author jecc
 * 
 */
@Entity
@Table(indexes = {@Index(name = "IDX_PHONE_SERIAL_NUMBER", columnList = "phoneNumber,serialNumber"), @Index(name="UNQ_PHONE_SERIAL_NUMBER_ID", columnList="phoneNumber,serialNumber,terminal_id", unique=true)})
public class SimCard extends BasePersistable<Long> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8159868743240389421L;
	/**
	 * 终端模型对象
	 */
	@OneToOne  
    @JoinColumn(name="terminal_id") 
	private BusTerminal busTerminal;
	/**
	 * 手机号码
	 */
	private String phoneNumber;

	/**
	 * 序列号
	 */
	private String serialNumber;
	/**
	 * 是否可用.
	 */
	private Byte isEnable;
	/***
	 * SIM状态
	 * 
	 * @author jecc
	 * 
	 */
	public enum isEnable {

		/** 可用. **/
		Enable((byte) 0),
		/** 不可用. **/
		Disable((byte) 1);

		private byte type;

		private isEnable(byte type) {
			this.type = type;
		}

		public byte getType() {
			return this.type;
		}

	}
	/**
	 * 传输模式
	 */
	@Column(columnDefinition = "smallint default 0")
	private Byte transferMode;
	/**
	 * SIM识别模式
	 * 
	 * @author jecc
	 * 
	 */
	public enum TransferMode {
		/** 3G传输 */
		G3((byte) 2),
		/** 4G传输 */
		G4((byte) 1),
		/** Wifi传输 */
		Wifi((byte) 0),
		/**
		 * 其他模式
		 */
		Other((byte) 0);

		private Byte transferStatus;

		public Byte getTransferStatus() {
			return transferStatus;
		}

		private TransferMode(Byte status) {
			this.transferStatus = status;
		}

	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Byte getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Byte isEnable) {
		this.isEnable = isEnable;
	}
	public Byte getTransferMode() {
		return transferMode;
	}
	public void setTransferMode(Byte transferMode) {
		this.transferMode = transferMode;
	}
	public BusTerminal getBusTerminal() {
		return busTerminal;
	}
	public void setBusTerminal(BusTerminal busTerminal) {
		this.busTerminal = busTerminal;
	}
}

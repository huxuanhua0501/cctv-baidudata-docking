/** 
* Copyright @2012-2016 Busap.com All Rights Reserved. 
*/ 
package com.busap.cctv.baidudata.docking.domain; 

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The Class Terminal.
 * 
 * @author liweizhi
 * @since 2014年11月7日
 * @description: 公交设备
 */
@Entity
@DiscriminatorValue(value="bus")  
public class BusTerminal extends Terminal {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1392143447459935000L;


	/** 所属线路. */
	@ManyToOne
	@JoinColumn(name="line_id")
	private BusLine busLine;
	
	/** 车辆自编号. */
	private String busNo;

	/**
	 * Gets the 所属线路.
	 * 
	 * @return the 所属线路
	 */
	public BusLine getBusLine() {
		return busLine;
	}

	/**
	 * Sets the 所属线路.
	 * 
	 * @param busLine
	 *            the new 所属线路
	 */
	public void setBusLine(BusLine busLine) {
		this.busLine = busLine;
	}

	/**
	 * Gets the 车牌号.
	 * 
	 * @return the 车牌号
	 */
	public String getBusNo() {
		return busNo;
	}

	/**
	 * Sets the 车牌号.
	 * 
	 * @param busNo
	 *            the new 车牌号
	 */
	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}
	
/*	public String toString() {
		return "this busTerminal's busNo is:"+busNo+"\t busLine is:"+busLine.getName();
	}*/
	
}
          

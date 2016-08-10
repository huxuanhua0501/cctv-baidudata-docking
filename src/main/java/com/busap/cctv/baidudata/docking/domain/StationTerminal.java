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
 * @description: 场站设备
 */
@Entity
@DiscriminatorValue(value= "station")  
public class StationTerminal extends Terminal {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1661871881367390865L;

	/** 所属场站. */
	@ManyToOne
	@JoinColumn(name="ground_id")
	private Ground ground;
	
	/** ip地址. */
	private String ip;

	/**
	 * Gets the 所属场站.
	 * 
	 * @return the 所属场站
	 */
	public Ground getGround() {
		return ground;
	}

	/**
	 * Sets the 所属场站.
	 * 
	 * @param ground
	 *            the new 所属场站
	 */
	public void setGround(Ground ground) {
		this.ground = ground;
	}

	/* (non-Javadoc)
	 * @see com.busap.cctv.domain.Terminal#getIp()
	 */
	public String getIp() {
		return ip;
	}

	/* (non-Javadoc)
	 * @see com.busap.cctv.domain.Terminal#setIp(java.lang.String)
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

}
          

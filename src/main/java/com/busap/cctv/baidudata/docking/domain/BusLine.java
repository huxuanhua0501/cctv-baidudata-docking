/** 
* Copyright @2012-2016 Busap.com All Rights Reserved. 
*/ 
package com.busap.cctv.baidudata.docking.domain; 

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 公交线路实体.
 * 
 * @author liweizhi
 * @since 2014年11月6日
 * @description:公交线路
 */
@Entity
public class BusLine extends BasePersistable<Long> {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2116683878208549226L;

	/** 线路名称. */
	private String name;
	
	/** 所属公交公司. */
	@ManyToOne
	@JoinColumn(name="company_id",nullable = false)
	private BusCompany busCompany;

	/**  线路所属区域. */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "area_id", nullable = false)
	private Area area;
	
	/** 负责人. */
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	/**
	 * Gets the 线路名称.
	 * 
	 * @return the 线路名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the 线路名称.
	 * 
	 * @param name
	 *            the new 线路名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the 所属公交公司.
	 * 
	 * @return the 所属公交公司
	 */
	public BusCompany getBusCompany() {
		return busCompany;
	}

	/**
	 * Sets the 所属公交公司.
	 * 
	 * @param busCompany
	 *            the new 所属公交公司
	 */
	public void setBusCompany(BusCompany busCompany) {
		this.busCompany = busCompany;
	}

	/**
	 * Gets the 线路所属区域.
	 * 
	 * @return the 线路所属区域
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * Sets the 线路所属区域.
	 * 
	 * @param area
	 *            the new 线路所属区域
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * Gets the 负责人.
	 * 
	 * @return the 负责人
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the 负责人.
	 * 
	 * @param user
	 *            the new 负责人
	 */
	public void setUser(User user) {
		this.user = user;
	}

	
}
          

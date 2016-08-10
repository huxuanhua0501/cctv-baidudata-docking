package com.busap.cctv.baidudata.docking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 公交公司信息
 * @author chaijunkun
 * @since 2014年11月7日
 */
@Entity
@Table(indexes = {@Index(columnList="area_id")})
public class BusCompany extends BasePersistable<Long>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5485782404979504343L;

	/**
	 * 公交公司归属区域
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "area_id", nullable = false)
	private Area area;
	
	/**
	 * 公交公司名称
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * 公交公司联系人
	 */
	private String contact;
	
	/**
	 * 公交公司联系电话
	 */
	private String tel;

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
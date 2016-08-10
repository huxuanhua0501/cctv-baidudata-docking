package com.busap.cctv.baidudata.docking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 场站信息
 * @author chaijunkun
 * @since 2014年11月7日
 */
@Entity
@Table(name = "ground", indexes = {@Index(columnList="area_id")})
public class Ground extends BasePersistable<Long>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4323616209846703639L;

	/**
	 * 场站名称
	 */
	@Column(nullable = false)
	private String name;
	
	/**
	 * 场站归属区域
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "area_id")
	private Area area;
	
	/**
	 * 场站联系人
	 */
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	/**
	 * 场站联系电话
	 */
	private String tel;

	/**
	 * 场站地址
	 */
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
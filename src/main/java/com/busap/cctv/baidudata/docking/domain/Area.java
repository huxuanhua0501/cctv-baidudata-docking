package com.busap.cctv.baidudata.docking.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * 区域地理信息记录
 * @author chaijunkun
 * @since 2014年11月6日
 */
@Entity
@Table(indexes={@Index(name = "IDX_PARENT_ID", columnList="parent_id"), @Index(name = "IDX_CODE", columnList="code", unique= true)})
public class Area extends BasePersistable<Long>  {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4344389282700435966L;

	/**
	 * 区号
	 */
	@Column(nullable = false, unique = true)
	private String code;
	
	/**
	 * 区域名称
	 */
	@Column(nullable = false)
	private String name;
	
	
	/**
	 * 父区域id
	 */
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Area parent;
	
	/**
	 * 区域负责人id
	 */
	@OneToOne
	@JoinColumn(name = "user_id",insertable=false)
	private User user;
	
	/** 排序优先级(正整数) */
	@Column(nullable=true)
	private byte adjustedSort;
	
	/**
	 * 城市与用户的一对多的关系模型
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
	private Set<User> users=new HashSet<User>();
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Area getParent() {
		return parent;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public byte getAdjustedSort() {
		return adjustedSort;
	}

	public void setAdjustedSort(byte adjustedSort) {
		this.adjustedSort = adjustedSort;
	}





	
}
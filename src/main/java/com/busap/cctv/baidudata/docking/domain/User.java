package com.busap.cctv.baidudata.docking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户模型.
 * 
 * @author lidong
 */
@Entity
@Table(name = "users")
public class User extends BasePersistable<Long> implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8674261997970620107L;

	
	public enum UserType {

		/** 超级管理员. **/
		Super((byte) 0),
		/** 普通管理员. **/
		Normal((byte) 1);

		private byte type;

		private UserType(byte type) {
			this.type = type;
		}

		public byte getType() {
			return this.type;
		}
		
	}
	
	/** 登陆名. */
	@Column(name = "name",unique=true, nullable = false,updatable=false)
	private String name;

	/** 密码. */
	private String password;

	/** 盐. */
	private String salt;

	/** 全名(真实姓名). */
	@Column(nullable = false)
	private String fullName;

	/** 联系电话. */
	private String tel;

	/** 锁. false锁定false解锁 */
    private boolean locked;
    
	/** level 是否是超级管理员  0是超级管理员 1其他管理 */
	@Column(name="isAdmin",nullable = false)
    private Byte isAdmin;
    
	/** 用户所属于区域. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "arer_id")
	private Area area;
	
	/** 用户所属于角色. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;

	
	/**
	 * 获取name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name.
	 * 
	 * @param name
	 *            要设置的name值
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取password.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置password.
	 * 
	 * @param password
	 *            要设置的password值
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取salt.
	 * 
	 * @return salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * 设置salt.
	 * 
	 * @param salt
	 *            要设置的salt值
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * 获取full name.
	 * 
	 * @return full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 设置full name.
	 * 
	 * @param fullName
	 *            要设置的full name值
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * 获取tel.
	 * 
	 * @return tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * 设置tel.
	 * 
	 * @param tel
	 *            要设置的tel值
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Byte getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Byte isAdmin) {
		this.isAdmin = isAdmin;
	}



	

}

package com.busap.cctv.baidudata.docking.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 角色模型。
 * 
 * @author lodong
 * @since 2014-11-06
 */
@Entity
@Table(name = "roles")
public class Role extends BasePersistable<Long> {
	private static final long serialVersionUID = -2547025254631490133L;

	
	public enum UserType {

		/** 集团管理岗位. **/
		Super((byte) 0),
		/** 分公司领导岗位. **/
		Area((byte) 1),
		/** 普通用户岗位. **/
		Normal((byte) 2);

		private byte type;

		private UserType(byte type) {
			this.type = type;
		}

		public byte getType() {
			return this.type;
		}
		
	}
	
	/** 角色 */
	private String name;

	/** 是否可用true：可用，false：禁用 */
	private boolean enable;

	/** 描述 */
	private String description;

	/** 岗位： 0 集团管理岗位,2分公司领导岗位 3普通用户岗位 */
	@Column(name="position",nullable = false)
	private Byte  position;
	
	/** 用户于角色一对多关联关系表 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private Set<User> users=new HashSet<User>();
	
	/** 角色与资源的多对多关系表 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_resource", joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "resource_id", nullable = false, updatable = false) })
	private Set<Resource> resources;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Byte getPosition() {
		return position;
	}

	public void setPosition(Byte position) {
		this.position = position;
	}

	
}
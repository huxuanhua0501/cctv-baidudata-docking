package com.busap.cctv.baidudata.docking.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 资源模型。
 * 
 * @author lidong
 * @since 2014-11-06
 */
@Entity
@Table(name = "resource")
public class Resource extends BasePersistable<Long>{

	private static final long serialVersionUID = -4938283029379201570L;

	/** 权限名称 */
	@Column(name = "name", nullable = false)
    private String name;

	/** 父级id */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_Id")
	private Resource parent;

	/** 权限 */
    private String permission;

    /** 是否可用true：可用，false：禁用 */
    private boolean enable;

    /** 是否可用1：功能，0：菜单 */
    private boolean  type;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_resource", joinColumns = { @JoinColumn(name = "resource_id", nullable = false, updatable = false) }, 
	inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	private Set<Role> roles;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
	private Set<Resource> childResources;

    
    


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public Set<Resource> getChildResources() {
		return childResources;
	}

	public void setChildResources(Set<Resource> childResources) {
		this.childResources = childResources;
	}


}

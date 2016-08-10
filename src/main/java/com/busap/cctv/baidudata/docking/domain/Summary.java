package com.busap.cctv.baidudata.docking.domain; 

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

/** 
 * 摘要文件信息
 * @author chaijunkun
 * @since 2014年11月17日 
 */
@Entity
public class Summary extends AbstractPersistable<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1671553212270713860L;

	/**
	 * 摘要文件版本号
	 */
	@Column(nullable = false, unique = true)
	public Integer version;
	
	/**
	 * 加密摘要文件云存储路径
	 */
	@Column(nullable = false)
	public String uri;
	
	/**
	 * 同步时间
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date syncDate;
	
	/**
	 * 该摘要是否启用
	 */
	@Column(nullable = false)
	public Boolean enabled;

	/**
	 * 获取摘要文件版本号
	 * @return 摘要文件版本号
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * 设置摘要文件版本号
	 * @param version 摘要文件版本号
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * 获取加密摘要文件云存储路径
	 * @return加密摘要文件云存储路径
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * 设置加密摘要文件云存储路径
	 * @param uri 加密摘要文件云存储路径
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * 获取同步时间
	 * @return 同步时间
	 */
	public Date getSyncDate() {
		return syncDate;
	}

	/**
	 * 设置同步时间
	 * @param syncDate 同步时间
	 */
	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	/**
	 * 获取该摘要是否启用
	 * @return 该摘要是否启用
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * 设置该摘要是否启用
	 * @param enabled 该摘要是否启用
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}

package com.busap.cctv.baidudata.docking.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.google.common.base.Objects;

/**
 * 基本数据表中共有的字段定义
 * @author chaijunkun
 * @since 2014年11月6日
 * @param <PK> 主键类型
 */
@MappedSuperclass
public abstract class BasePersistable<PK extends Serializable> extends AbstractPersistable<PK> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4014048009168046333L;
	
	public BasePersistable(){}
	
	public BasePersistable(PK id){
		super.setId(id);
	}
	
	/***
	 * 记录持久化状态
	 * @author chaijunkun
	 *
	 */
	public enum Status {
		/**状态 无效 */
		Delete((short)0),
		/**状态 刚插入未激活 */
		Normal((short)1),
		/**状态 激活 */
		Archive((short)2);
		
		private short status;
		
		private Status(short status){
			this.status = status;
		}
		
		public short getStatus() {
			return this.status;
		}
	}
	
	/**
	 * 数据持久化状态
	 */
	@Column(nullable = false)
	private Short status;
	
	/**
	 * 本条记录创建者
	 */
	@Column(nullable = false)
	private Long creatorId;
	
	/**
	 * 本条记录创建时间
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	/**
	 * 本条记录最后修改者
	 */
	@Column(nullable = true)
	private Long modifierId;
	
	/**
	 * 本条记录最后修改时间
	 */
	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;
	
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getModifierId() {
		return modifierId;
	}
	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("status", status)
		.add("creatorId", creatorId)
		.add("createTime", createTime)
		.add("modifierId", modifierId)
		.add("modifyTime", modifyTime)
		.toString();
	}
}

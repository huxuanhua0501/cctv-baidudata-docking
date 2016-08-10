package com.busap.cctv.baidudata.docking.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

import java.util.Date;

/**
 * 播放列表信息
 * @author chaijunkun
 * @since 2014年11月7日
 */
@Entity
@Table(indexes = {@Index(name = "IDX_REF_TYPE_REF_ID", columnList = "refType,refId"), @Index(name="UNQ_REF_TYPE_REF_ID_VERSION", columnList="refType,refId,version", unique=true)})
public class Play extends AbstractPersistable<Long>  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5702249385572114461L;

	/**
	 * 播放列表引用类型
	 * @author chaijunkun
	 * 
	 */
	public enum RefType{
		/** 按区域 */
		Area((short)1),
		/** 按公交公司 */
		BusCompany((short)2),
		/** 按线路 */
		Line((short)3),
		/** 按场站 */
		Ground((short)4);
		
		private short type;
		
		private RefType(short type){
			this.type = type;
		}
		
		public short getType(){
			return this.type;
		}
	}
	
	
	/***
	 * 记录持久化状态
	 * @author chaijunkun
	 *
	 */
	public enum Status {
		/**状态 无效 */
		Delete((short)0),
		/**状态 上线状态 */
		Normal((short)1),
		/**状态 下线 */
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
	 * 播放列表版本划分类类型
	 */
	@Column(nullable = false)
	private Short refType;

	/**
	 * 播放列表版本划分在对应分类类型下的主表主键
	 */
	@Column(nullable = false)
	private Long refId;
	
	/**
	 * 播放列表版本号
	 */
	@Column(nullable = false)
	private Integer version;

	/**
	 * 播放列表的md5
	 */
	@Column(nullable = false)
	private  String md5;
	/**
	 * 执行该播放列表的日期
	 */
	@Temporal(TemporalType.DATE)
	private Date date;
	
	/**
	 * 本条记录创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	

	/**
	 * 加密播放列表的云存储路径
	 */
	private String uri;
	
	public Short getRefType() {
		return refType;
	}

	public void setRefType(Short refType) {
		this.refType = refType;
	}

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
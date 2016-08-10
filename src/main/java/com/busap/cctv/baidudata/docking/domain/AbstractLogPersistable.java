package com.busap.cctv.baidudata.docking.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * 基本日志数据表中共有的字段定义
 * @author jecc
 * @since 2016年7月4日
 * @param <PK> 主键类型
 */
@MappedSuperclass
public class AbstractLogPersistable<PK extends Serializable> extends AbstractPersistable<PK> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1655865134884957561L;

	
	public AbstractLogPersistable(){}
	
	public AbstractLogPersistable(PK id){
		super.setId(id);
	}
	/**
	 * 本条记录创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	/**
	 * 本条记录最后修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}

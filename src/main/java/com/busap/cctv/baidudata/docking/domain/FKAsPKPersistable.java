package com.busap.cctv.baidudata.docking.domain; 

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.domain.Persistable;

/** 
 * 外键作为主键的持久化抽象类
 * @author chaijunkun
 * @since 2014年11月12日 
 * @param <PK> 主键类型
 */
@MappedSuperclass
public abstract class FKAsPKPersistable<PK extends Serializable> implements Persistable<PK>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8497856010198035936L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id")
	private PK id;
	
	@Override
	public PK getId() {
		return id;
	}

	@Override
	public boolean isNew() {
		return null == getId();
	}

}

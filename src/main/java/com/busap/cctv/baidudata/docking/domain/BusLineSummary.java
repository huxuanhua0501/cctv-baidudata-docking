package com.busap.cctv.baidudata.docking.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * 公交线路汇总实体.
 * 
 * @author jecc
 * @since 2014年11月6日
 * @description:公交线路汇总
 */
@Entity
@Table(indexes = {@Index(name = "IDX_AREA_ID_BUSLINE_ID_PLAY_ID_VERSION", columnList = "area_id,company_id,line_id,version")})
public class BusLineSummary extends AbstractPersistable<Long> {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -71212933182983023L;

	/**
	 * 城市
	 */
	@ManyToOne(fetch=FetchType.LAZY,optional=true )
	@JoinColumn(name = "area_id")
	private Area area;
	
	/** 所属公交公司. */
	@ManyToOne(fetch = FetchType.LAZY,optional=true )
	@JoinColumn(name = "company_id")
	private BusCompany busCompany;
	
	
	/** 所属线路. */
	@ManyToOne(fetch = FetchType.LAZY,optional=true )
	@JoinColumn(name = "line_id")
	private BusLine busLine;
	
    /**
     * 版本
     */
    private Integer version;

    /**
     * 终端数量
     */
	private Long terminalSum;
	 /**
     * 完成终端
     */
	private Integer complete;  
	 /**
     * 连接数
     */
	private Integer connect;

	 /**
     * 自动更新
     */
	private Integer autoUpdate;
	 /**
     * 非自动更新
     */
	private Integer notAutoUpdate;
	
	/**
	 * 本条记录创建时间
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
    /**
     * 本条记录修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    
    
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public BusCompany getBusCompany() {
		return busCompany;
	}
	public void setBusCompany(BusCompany busCompany) {
		this.busCompany = busCompany;
	}
	public BusLine getBusLine() {
		return busLine;
	}
	public void setBusLine(BusLine busLine) {
		this.busLine = busLine;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Long getTerminalSum() {
		return terminalSum;
	}
	public void setTerminalSum(Long terminalSum) {
		this.terminalSum = terminalSum;
	}
	public Integer getComplete() {
		return complete;
	}
	public void setComplete(Integer complete) {
		this.complete = complete;
	}
	public Integer getConnect() {
		return connect;
	}
	public void setConnect(Integer connect) {
		if (connect<0) {
			this.connect = 0;
		}else {
			this.connect = connect;
		}
	}
	public Integer getAutoUpdate() {
		return autoUpdate;
	}
	public void setAutoUpdate(Integer autoUpdate) {
		this.autoUpdate = autoUpdate;
	}
	public Integer getNotAutoUpdate() {
		return notAutoUpdate;
	}
	public void setNotAutoUpdate(Integer notAutoUpdate) {
		this.notAutoUpdate = notAutoUpdate;
	}
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

package com.busap.cctv.baidudata.docking.domain;

import java.util.Date;

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
 * 终端传输进度实体
 * @author jecc
 *
 */
@Entity
@Table(indexes = {@Index(name = "IDX_BUS_ID_PLAY_ID", columnList = "bus_id,play_id"), @Index(name="UNQ_BUS_ID_PLAY_ID_LINE_ID", columnList="bus_id,play_id,line_id", unique=true)})
public class TerminalProgress extends AbstractPersistable<Long>{

	   /***
     * 版本更新情况
     * @author jecc
     *
     */
    public enum Status {
        /**未更新 */
        No((short)0),
        /**更新中 */
        Doing((short)1),
        /**更新完成 */
        Done((short)2);

        private short status;

        private Status(short status){
            this.status = status;
        }

        public short getStatus() {
            return this.status;
        }

		public void setStatus(short status) {
			this.status = status;
		}
    }
    /**
     * 终端所属场站
     */
    @Column(nullable = false)
    private String stationAppKey;
    /**
     * 公交终端ID
     */
    @ManyToOne(fetch = FetchType.LAZY,optional=true)
    @JoinColumn(name = "bus_id")
    private BusTerminal busTerminal;
    

	/** 所属线路. */
	  @ManyToOne(fetch = FetchType.LAZY,optional=true)
	@JoinColumn(name="line_id")
	private BusLine busLine;
    /**
     * 版本
     */
    @ManyToOne(fetch = FetchType.LAZY,optional=true)
    @JoinColumn(name = "play_id")
    private Play play;

    /**更新情况百分比*/
    @Column(nullable = false)
    private String note;
    /**
     * 版本更新情况状态
     */
    @Column(nullable = false)
    private Short status;
	 /**
    * 是否自动更新
    */
	private Boolean autoUpdate;
    
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
    
    
	public String getStationAppKey() {
		return stationAppKey;
	}
	public void setStationAppKey(String stationAppKey) {
		this.stationAppKey = stationAppKey;
	}
	public BusTerminal getBusTerminal() {
		return busTerminal;
	}
	public void setBusTerminal(BusTerminal busTerminal) {
		this.busTerminal = busTerminal;
	}
	public Play getPlay() {
		return play;
	}
	public void setPlay(Play play) {
		this.play = play;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public BusLine getBusLine() {
		return busLine;
	}
	public void setBusLine(BusLine busLine) {
		this.busLine = busLine;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Boolean getAutoUpdate() {
		return autoUpdate;
	}
	public void setAutoUpdate(Boolean autoUpdate) {
		this.autoUpdate = autoUpdate;
	}
    
    
    
}

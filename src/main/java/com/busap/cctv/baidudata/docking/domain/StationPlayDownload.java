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
 * Created by lwz on 15-6-23.
 * 基站版本下载情况实体类
 */
@Entity
@Table(indexes = {@Index(name = "IDX_STATION_ID_PLAY_ID", columnList = "station_id,play_id"), @Index(name="UNQ_BUS_ID_PLAY_ID_GROURD_ID", columnList="station_id,play_id,ground_id", unique=true)})
public class StationPlayDownload extends AbstractPersistable<Long> {

    /***
     * 版本更新情况
     * @author chaijunkun
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
    }


    /**
     * 基站
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id")
    private StationTerminal stationTerminal;

	/** 所属场站. */
	@ManyToOne(fetch = FetchType.LAZY,optional=true)
	@JoinColumn(name="ground_id")
	private Ground ground;
    
    /**
     * 版本
     */
    @ManyToOne(fetch = FetchType.LAZY,optional=true)
    @JoinColumn(name = "play_id")
    private Play play;

    /**更新情况百分比*/
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


    public StationTerminal getStationTerminal() {
        return stationTerminal;
    }

    public void setStationTerminal(StationTerminal stationTerminal) {
        this.stationTerminal = stationTerminal;
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

	public Ground getGround() {
		return ground;
	}

	public void setGround(Ground ground) {
		this.ground = ground;
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

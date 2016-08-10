package com.busap.cctv.baidudata.docking.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 分时音量
 * 
 * @author jecc
 * 
 */
@Entity
@Table(name="volume_timeshare",indexes = {@Index(name = "IDX_SHARE_PLAN_ID", columnList = "plan_id")})
public class TimeshareVolume extends BasePersistable<Long> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8061810029345677751L;

	/**
	 * 方案ID.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "plan_id",nullable = false)
	private VolumePlan volumePlan;

	/**
	 * 是否可用.
	 */
	private Byte isEnable;
	/**
	 * 开始时间.
	 * 
	 */
	private String startTimes;
	/**
	 * 结束时间.
	 * 
	 */
	private String endTimes;
	/**
	 * 音量.
	 */
	private Short volume;

	/**
	 * 序列.
	 */
	private Short seq;

	public enum IsEnable {

		/** 不可用. **/
		Disable((byte) 0),
		/** 可用. **/
		Enable((byte) 1);

		private byte type;

		private IsEnable(byte type) {
			this.type = type;
		}

		public byte getType() {
			return this.type;
		}

	}

	public VolumePlan getVolumePlan() {
		return volumePlan;
	}

	public void setVolumePlan(VolumePlan volumePlan) {
		this.volumePlan = volumePlan;
	}

	public Byte getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Byte isEnable) {
		this.isEnable = isEnable;
	}

	public String getStartTimes() {
		return startTimes;
	}

	public void setStartTimes(String startTimes) {
		this.startTimes = startTimes;
	}

	public String getEndTimes() {
		return endTimes;
	}

	public void setEndTimes(String endTimes) {
		this.endTimes = endTimes;
	}

	public Short getVolume() {
		return volume;
	}

	public void setVolume(Short volume) {
		this.volume = volume;
	}

	public Short getSeq() {
		return seq;
	}

	public void setSeq(Short seq) {
		this.seq = seq;
	}
}

package com.busap.cctv.baidudata.docking.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 音量方案
 * 
 * @author jecc
 * 
 */

@Entity(name="volumePlan")
@Table(name="volume_plan")
public class VolumePlan extends BasePersistable<Long> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7747296037875485733L;
	/**
	 * 方案名称.
	 */
	private String name;
	/**
	 * 描述.
	 */
	private String descriptions;
	/**
	 * 默认音量.
	 */
	private Short defaultVolume;
	/**
	 * 是否可用.
	 */
	private Byte isEnable;
	/**
	 * 是否审核
	 */
	private Byte isVeryfied;
	/**
	 * 拒绝原因
	 */
	private String refuseReason;
	/**
	 * xml文件上传至分布式文件系统的路径
	 */
	private String fdfsUrl;
	/**
	 * 文件通过hash算法生成md5值
	 */
	private String fileMD5;
	/**
	 * 创建用户所在城市
	 */
	private Long areaId;

	/**
	 * 多个分时音量.
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "volumePlan",cascade=CascadeType.ALL)
	private List<TimeshareVolume> timeshareVolumes = new LinkedList<TimeshareVolume>();

	public enum IsVeryfied {

		/** 拒绝. **/
		Refuse((byte) -1,"拒绝"),
		/** 未审核. **/
		UnVeryfied((byte) 0,"未审核"),
		/** 普用户是审核中,管理员用户是未审核. **/
		Veryfing((byte)1,""),
		/** 已审核. **/
		Veryfied((byte) 2,"已审核");

		private byte type;
		private String meaning;

		private IsVeryfied(byte type,String meaning) {
			this.type = type;
			this.meaning = meaning;
		}

		public byte getType() {
			return type;
		}

		public void setType(byte type) {
			this.type = type;
		}

		public String getMeaning() {
			return meaning;
		}

		public void setMeaning(String meaning) {
			this.meaning = meaning;
		}

		public static String getIsVeriedMeaning(final byte type){
			for (IsVeryfied isVeryfied : IsVeryfied.values()) {
				if (isVeryfied.getType() == type){
					return isVeryfied.getMeaning();
				}
			}
			return "未知";
		}

	}
	public enum IsEnable {

		/** 不可用. **/
		Disable((byte) 0,"不可用"),
		/** 可用. **/
		Enable((byte) 1,"可用");

		private byte type;
		private String meaning;

		private IsEnable(byte type,String meaning) {
			this.type = type;
			this.meaning = meaning;
		}

		public byte getType() {
			return this.type;
		}
		public String getMeaning() {
			return meaning;
		}
		public void setMeaning(String meaning) {
			this.meaning = meaning;
		}
		public void setType(byte type) {
			this.type = type;
		}

		public static String getIsEnableMeaning(final byte type){
			for (IsEnable isEnable : IsEnable.values()) {
				if (isEnable.getType() == type){
					return isEnable.getMeaning();
				}
			}
			return "未知";
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Short getDefaultVolume() {
		return defaultVolume;
	}

	public void setDefaultVolume(Short defaultVolume) {
		this.defaultVolume = defaultVolume;
	}

	public Byte getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Byte isEnable) {
		this.isEnable = isEnable;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public  List<TimeshareVolume> getTimeshareVolumes() {
		return timeshareVolumes;
	}

	public void setTimeshareVolumes(List<TimeshareVolume> timeshareVolumes) {
		this.timeshareVolumes = timeshareVolumes;
	}

	public Byte getIsVeryfied() {
		return isVeryfied;
	}

	public void setIsVeryfied(Byte isVeryfied) {
		this.isVeryfied = isVeryfied;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getFdfsUrl() {
		return fdfsUrl;
	}

	public void setFdfsUrl(String fdfsUrl) {
		this.fdfsUrl = fdfsUrl;
	}

	public String getFileMD5() {
		return fileMD5;
	}

	public void setFileMD5(String fileMD5) {
		this.fileMD5 = fileMD5;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	
	
}

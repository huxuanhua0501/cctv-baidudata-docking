package com.busap.cctv.baidudata.docking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * 媒体信息.
 * @author chaijunkun
 * @since 2014年11月7日
 */
@Entity
public class PlayMedia extends AbstractPersistable<Long> {
	
	/** 序列号. */
	private static final long serialVersionUID = -6480381762302669309L;

	public PlayMedia() {
	
	}

	public PlayMedia(MediaLib mediaLib,String title, short type) {
		super();
		this.mediaLib = mediaLib;
		this.title=title;
		this.type=type;
	}
	
	public PlayMedia(String title,short type, String layout,int duration,String contractId) {
		super();
		this.title=title;
		this.type=type;
		this.layout=layout;
		this.duration=duration;
		this.contractId=contractId;
	}

	/**
	 * 媒体类型枚举定义.
	 *
	 * @author chaijunkun
	 */
	public enum MediaType {
		/** 媒体类型-未知 */
		UNKNOWN((short) 0, "未知"),
		/** 媒体类型-节目 */
		PROGRAM((short) 1, "节目"),
		/** 媒体类型-广告 */
		AD((short) 2, "广告");
		/** 媒体类型的数据表示形式 */
		private short type;
		/** 媒体类型描述 */
		private String desc;

		/**
		 * 私有默认构造函数.
		 * @param type 媒体类型的数据表示形式
		 * @param desc 媒体类型描述
		 */
		private MediaType(final short type, final String desc) {
			this.type = type;
			this.desc = desc;
		}

		/**
		 * Gets the 媒体类型的数据表示形式.
		 * @return the 媒体类型的数据表示形式
		 */
		public short getType() {
			return this.type;
		}

		/**
		 * Gets the 媒体类型描述.
		 * @return the 媒体类型描述
		 */
		public String getDesc() {
			return desc;
		}

		/**
		 * 根据数字返回枚举描述.
		 * @param type 类型
		 * @return 枚举的描述，如果没有则返回UNKNOWN描述
		 */
		public static String getDescByType(final short type) {
			for (MediaType mediaType : MediaType.values()) {
				if (mediaType.getType() == type){
					return mediaType.getDesc();
				}
			}
			return UNKNOWN.getDesc();
		}
		
		/**
		 * 根据媒体类型描述返回枚举类型值
		 * @param desc 描述
		 * @return 枚举的类型值,如果没有匹配到则返回UNKNOWN值
		 */
		public static short getTypeByDesc(final String desc) {
			for (MediaType mediaType : MediaType.values()) {
				if (mediaType.getDesc().equals(desc)){
					return mediaType.getType();
				}
			}
			return UNKNOWN.getType();
		}
	}

	/** 播放列表id. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "play_id", nullable = false)
	private Play play;
	
	/** 媒体文件 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "media_lib_id", nullable = false)
	private MediaLib mediaLib;

	/** 媒体类型. */
	@Column(nullable = false)
	private Short type;

	/** 合同号(媒体类型是广告时必填). */
	private String contractId;

	/** 媒体标题. */
	private String title;

	/** 播放时长(单位:秒). */
	private int duration;
	
	/** 节目编排在一天中的何时播出 */
	private String layout;

	/**
	 * 获取播放列表id.
	 * @return 播放列表id
	 */
	public Play getPlay() {
		return play;
	}

	/**
	 * 设置播放列表id.
	 * @param play 已设置播放列表id的对象
	 */
	public void setPlay(Play play) {
		this.play = play;
	}

	/**
	 * Gets the 媒体文件.
	 *
	 * @return the 媒体文件
	 */
	public MediaLib getMediaLib() {
		return mediaLib;
	}

	/**
	 * Sets the 媒体文件.
	 *
	 * @param mediaLib
	 *            the new 媒体文件
	 */
	public void setMediaLib(MediaLib mediaLib) {
		this.mediaLib = mediaLib;
	}

	/**
	 * 获取媒体类型.
	 * @return 媒体类型
	 */
	public Short getType() {
		return type;
	}

	/**
	 * 设置媒体类型.
	 * @param type 媒体类型
	 */
	public void setType(Short type) {
		this.type = type;
	}

	/**
	 * 获取合同号(媒体类型是广告时必填).
	 * @return 合同号(媒体类型是广告时必填)
	 */
	public String getContractId() {
		return contractId;
	}

	/**
	 * 设置合同号(媒体类型是广告时必填).
	 * @param contractId 合同号(媒体类型是广告时必填)
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	/**
	 * 获取媒体标题.
	 *
	 * @return 媒体标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置媒体标题.
	 *
	 * @param title
	 *            媒体标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取播放时长(单位:秒).
	 *
	 * @return 播放时长(单位:秒)
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * 设置播放时长(单位:秒).
	 * @param duration 播放时长(单位:秒)
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	/**
	 * 获取节目编排在一天中的何时播出
	 * @return 节目编排在一天中的何时播出
	 */
	public String getLayout() {
		return layout;
	}

	/**
	 * 设置节目编排在一天中的何时播出
	 * @param layout 节目编排在一天中的何时播出
	 */
	public void setLayout(String layout) {
		this.layout = layout;
	}

}
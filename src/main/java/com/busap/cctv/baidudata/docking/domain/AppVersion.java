package com.busap.cctv.baidudata.docking.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.common.base.Objects;

/**
 * 应用版本.
 *
 * @author wangwei
 */
@Entity
public class AppVersion extends BasePersistable<Long> {

	/** serialVersionUID. */
	private static final long serialVersionUID = -3886839918549694932L;

	/** 所属于的应用 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "app_id", nullable = false)
	private App app;

	/** 文件服务器路径,如: group1/M00/abc/ddd/ccc/sldk.txt . */
	private String uri;

	/** 文件原始名称. */
	private String fileName;

	/** 版本号. */
	private String version;

	/** 否强制更新，0不是，1是. */
	private Boolean mustUpdate;

	/**
	 * Gets the 所属于的应用.
	 *
	 * @return the 所属于的应用
	 */
	public App getApp() {
		return app;
	}

	/**
	 * Sets the 所属于的应用.
	 *
	 * @param app
	 *            the new 所属于的应用
	 */
	public void setApp(App app) {
		this.app = app;
	}

	/**
	 * Gets the 文件服务器路径,如: group1/M00/abc/ddd/ccc/sldk.txt.
	 *
	 * @return the 文件服务器路径,如: group1/M00/abc/ddd/ccc/sldk.txt
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Sets the 文件服务器路径,如: group1/M00/abc/ddd/ccc/sldk.txt.
	 *
	 * @param uri
	 *            the new 文件服务器路径,如: group1/M00/abc/ddd/ccc/sldk.txt
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * Gets the 文件名称.
	 *
	 * @return the 文件名称
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the 文件名称.
	 *
	 * @param fileName
	 *            the new 文件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the 版本号.
	 *
	 * @return the 版本号
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the 版本号.
	 *
	 * @param version
	 *            the new 版本号
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Gets the 否强制更新，0不是，1是.
	 *
	 * @return the 否强制更新，0不是，1是
	 */
	public Boolean getMustUpdate() {
		return mustUpdate;
	}

	/**
	 * Sets the 否强制更新，0不是，1是.
	 *
	 * @param mustUpdate
	 *            the new 否强制更新，0不是，1是
	 */
	public void setMustUpdate(Boolean mustUpdate) {
		this.mustUpdate = mustUpdate;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("app", app).add("uri", uri).add("fileName", fileName)
				.add("version", version).add("mustUpdate", mustUpdate).add("status", super.getStatus())
				.add("creatorId", super.getCreatorId()).add("createTime", super.getCreateTime())
				.add("modifierId", super.getModifierId()).add("modifyTime", super.getModifyTime()).toString();
	}

}

package com.busap.cctv.baidudata.docking.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.google.common.base.Objects;

/**
 * 应用实体类.
 */
@Entity
public class App extends BasePersistable<Long> {

	/** serialVersionUID. */
	private static final long serialVersionUID = -8323536322032119837L;

	/** 应用名称. */
	private String name;

	/** 应用类型 {@link AppType}. */
	private Short type;

	/** 应用公钥. */
	private String secret;

	/** 版本列表. */
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "app", fetch = FetchType.LAZY)
	private Set<AppVersion> versions;

	/**
	 * Gets the 应用名称.
	 *
	 * @return the 应用名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the 应用名称.
	 *
	 * @param name
	 *            the new 应用名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the 应用类型.
	 *
	 * @return the 应用类型
	 */
	public Short getType() {
		return type;
	}

	/**
	 * Sets the 应用类型.
	 *
	 * @param type
	 *            the new 应用类型
	 */
	public void setType(Short type) {
		this.type = type;
	}

	/**
	 * Gets the 应用公钥.
	 *
	 * @return the 应用公钥
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * Sets the 应用公钥.
	 *
	 * @param secret
	 *            the new 应用公钥
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * Gets the 版本列表.
	 *
	 * @return the 版本列表
	 */
	public Set<AppVersion> getVersions() {
		return versions;
	}

	/**
	 * Sets the 版本列表.
	 *
	 * @param versions
	 *            the new 版本列表
	 */
	public void setVersions(Set<AppVersion> versions) {
		this.versions = versions;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("name", name)
		.add("type", type)
		.add("secret", secret)
		.add("status", super.getStatus())
		.add("creatorId", super.getCreatorId())
		.add("createTime", super.getCreateTime())
		.add("modifierId", super.getModifierId())
		.add("modifyTime", super.getModifyTime())
		.toString();
	}

	/**
	 * 应用类型枚举
	 * 
	 * @author wangwei
	 *
	 */
	public enum AppType {

		/** 场站 */
		STATION((short) 1),
		/** 终端 */
		TERMINAL((short) 2);

		private short type;

		private AppType(short type) {
			this.type = type;
		}

		public short getType() {
			return this.type;
		}
	}

}

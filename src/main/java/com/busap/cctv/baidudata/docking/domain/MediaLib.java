package com.busap.cctv.baidudata.docking.domain; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.google.common.base.Objects;

/** 
 * 媒体文件库
 * @author chaijunkun
 * @since 2014年11月13日 
 */
@Entity
public class MediaLib extends AbstractPersistable<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4363453717528281443L;

	/**
	 * 媒体名称(用于标记文件大概内容,播放列表中不使用此名称)
	 */
	@Column(nullable = false)
	private String name;
	
	/**
	 * 媒体文件的校验名称
	 */
	@Column(nullable = false, unique = true)
	private String fileName;
	
	/**
	 * 媒体云存储路径
	 */
	@Column(nullable = false)
	private String uri;
	
	/**
	 * 媒体文件的MIME类型
	 */
	private String mime;
	
	/**
	 * 媒体文件的大小(单位:字节)
	 */
	private Long fileSize;
	
	/**
	 * 媒体分类(该分类由CCTV方维护)
	 */
	private String section;
	
	/**
	 * 媒体简介
	 */
	private String note;
	
	/**
	 * 媒体缩略图或封面图简称(不一定有)
	 */
	private String picName;
	
	/**
	 * 媒体缩略图或封面图校验名称(不一定有)
	 */
	private String picFileName;
	
	/**
	 * 媒体缩略图或封面图云存储路径(不一定有)
	 */
	private String picUri;
	
	/**
	 * 被包含的摘要文件
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "summary", nullable = false)
	private Summary summary;

	/**
	 * 获取媒体名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置媒体名称
	 * @param name 媒体名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取媒体文件的校验名称
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 设置媒体文件的校验名称
	 * @param fileName 媒体文件的校验名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 获取媒体云存储路径
	 * @return 媒体云存储路径
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * 设置媒体云存储路径
	 * @param uri 媒体云存储路径
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	/**
	 * 获取媒体文件的MIME类型
	 * @return 媒体文件的MIME类型
	 */
	public String getMime() {
		return mime;
	}
	
	/**
	 * 设置媒体文件的MIME类型
	 * @param mime 媒体文件的MIME类型
	 */
	public void setMime(String mime) {
		this.mime = mime;
	}
	
	/**
	 * 获取媒体文件的大小(单位:字节)
	 * @return 媒体文件的大小(单位:字节)
	 */
	public Long getFileSize() {
		return fileSize;
	}

	/**
	 * 设置媒体文件的大小(单位:字节)
	 * @param fileSize 媒体文件的大小(单位:字节)
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * 获取媒体分类
	 * @return 媒体分类
	 */
	public String getSection() {
		return section;
	}

	/**
	 * 设置媒体分类
	 * @param section 媒体分类
	 */
	public void setSection(String section) {
		this.section = section;
	}

	/**
	 * 获取媒体简介
	 * @return 媒体简介
	 */
	public String getNote() {
		return note;
	}

	/**
	 * 设置媒体简介
	 * @param note 媒体简介
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * 获取媒体缩略图或封面图简称(不一定有)
	 * @return 获取媒体缩略图或封面图简称(不一定有)
	 */
	public String getPicName() {
		return picName;
	}

	/**
	 * 设置媒体缩略图或封面图简称(不一定有)
	 * @param picName 媒体缩略图或封面图简称(不一定有)
	 */
	public void setPicName(String picName) {
		this.picName = picName;
	}

	/**
	 * 获取媒体缩略图或封面图校验名称(不一定有)
	 * @return 媒体缩略图或封面图校验名称(不一定有)
	 */
	public String getPicFileName() {
		return picFileName;
	}

	/**
	 * 设置媒体缩略图或封面图校验名称(不一定有)
	 * @param picFileName 媒体缩略图或封面图校验名称(不一定有)
	 */
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	/**
	 * 获取媒体缩略图或封面图云存储路径(不一定有)
	 * @return 媒体缩略图或封面图云存储路径(不一定有)
	 */
	public String getPicUri() {
		return picUri;
	}

	/**
	 * 设置媒体缩略图或封面图云存储路径(不一定有)
	 * @param picUri 媒体缩略图或封面图云存储路径(不一定有)
	 */
	public void setPicUri(String picUri) {
		this.picUri = picUri;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("name", name)
				.add("fileName", fileName).add("uri", uri)
				.add("mime", mime).add("section", section)
				.add("note", note).add("picName", picName)
				.add("picFileName", picFileName).add("picUri", picUri).toString();
	}

	/**
	 * 获取被包含的摘要文件
	 * @return 被包含的摘要文件
	 */
	public Summary getSummary() {
		return summary;
	}

	/**
	 * 设置被包含的摘要文件
	 * @param summary 被包含的摘要文件
	 */
	public void setSummary(Summary summary) {
		this.summary = summary;
	}
	
}

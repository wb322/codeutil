package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * domainCacheRefresh实体类
 * @author wubo
 */
@Entity
@Table(name = "domain_cache_refresh")
@DynamicInsert(true)
public class DomainCacheRefresh implements Serializable{

	/*** Id */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/*** 类型,1:预缓存 2:内容刷新 */
	private Integer type;
	/*** 开始时间 */
	private String start;
	/*** 结束时间 */
	private String end;
	/*** 内容 */
	private String content;
	@Transient
	private Integer contentCount;
	/*** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	private Boolean cdn;

	public Boolean getCdn() {
		return cdn;
	}

	public void setCdn(Boolean cdn) {
		this.cdn = cdn;
	}
	public Integer getContentCount() {
		return content.split("\n").length;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}


	
}

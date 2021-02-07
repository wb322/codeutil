package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * domain实体类
 * @author wubo
 */
@Entity
@Table(name = "domain")
@DynamicInsert(true)
public class Domain implements Serializable{

	/**
	 * id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
 	 * 域名
 	 */
	private String url;
	/**
 	 * 源地址
 	 */
	private String sourceUrl;


	/**
 	 * 回源方式:1主备,2轮询
 	 */
	private Integer returnSourceType;

	private Boolean cdn;

	public Boolean getCdn() {
		return cdn;
	}

	public void setCdn(Boolean cdn) {
		this.cdn = cdn;
	}
	/**
 	 * update_time
 	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;

	/** 同步状态 */
	private Boolean status;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "domain")
	private List<DomainCache> domainCaches;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "domain")
	private List<DomainHeader> domainHeaders;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public Integer getReturnSourceType() {
		return returnSourceType;
	}
	public void setReturnSourceType(Integer returnSourceType) {
		this.returnSourceType = returnSourceType;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<DomainCache> getDomainCaches() {
		return domainCaches;
	}

	public void setDomainCaches(List<DomainCache> domainCaches) {
		this.domainCaches = domainCaches;
	}

	public List<DomainHeader> getDomainHeaders() {
		return domainHeaders;
	}

	public void setDomainHeaders(List<DomainHeader> domainHeaders) {
		this.domainHeaders = domainHeaders;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Domain{" +
				"id=" + id +
				", url='" + url + '\'' +
				", sourceUrl='" + sourceUrl + '\'' +
				", returnSourceType=" + returnSourceType +
				", cdn=" + cdn +
				", updateTime=" + updateTime +
				", status=" + status +
				", domainCaches=" + domainCaches +
				", domainHeaders=" + domainHeaders +
				'}';
	}
}

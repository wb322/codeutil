package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * domainCache实体类
 * @author wubo
 */
@Entity
@Table(name = "domain_cache")
@DynamicInsert(true)
public class DomainCache implements Serializable{

	/**
	 * id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/**
 	 * 域名ID
 	 */
	private Integer domainId;
	/**
 	 * 1：文件类型 2：文件夹 3：全路径文件 4：首页
 	 */
	private Integer cacheType;
	/**
 	 * 缓存的内容
 	 */
	private String cacheContent;

	@JsonIgnore
	@Transient
	private String[] contents;

	public void setContents(String[] contents) {

	}

	public String[] getContents() {
		return cacheContent.split(";");
	}

	/**
 	 * 缓存时间
 	 */
	private Integer cacheTime;
	/**
 	 * d 天、h小时、m分、s秒
 	 */
	private String limitTime;
	/**
 	 * 优先级 数字越大越优先
 	 */
	private Integer weight;
	/**
 	 * 同步状态 1：待同步 2：同步成功 3：同步失败
 	 */
	private Integer status;

	private Boolean cdn;

	public Boolean getCdn() {
		return cdn;
	}

	public void setCdn(Boolean cdn) {
		this.cdn = cdn;
	}

	@ManyToOne
	@JoinColumn(name = "domainId",referencedColumnName = "id",updatable = false,insertable = false)
	private Domain domain;

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDomainId() {
		return domainId;
	}
	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}

	public Integer getCacheType() {
		return cacheType;
	}
	public void setCacheType(Integer cacheType) {
		this.cacheType = cacheType;
	}

	public String getCacheContent() {
		return cacheContent;
	}
	public void setCacheContent(String cacheContent) {
		this.cacheContent = cacheContent;
	}

	public Integer getCacheTime() {
		return cacheTime;
	}
	public void setCacheTime(Integer cacheTime) {
		this.cacheTime = cacheTime;
	}

	public String getLimitTime() {
		return limitTime;
	}
	public void setLimitTime(String limitTime) {
		this.limitTime = limitTime;
	}

	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DomainCache{" +
				"id=" + id +
				", domainId=" + domainId +
				", cacheType=" + cacheType +
				", cacheContent='" + cacheContent + '\'' +
				", cacheTime=" + cacheTime +
				", limitTime='" + limitTime + '\'' +
				", weight=" + weight +
				", status=" + status +
				", cdn=" + cdn +
				'}';
	}
}

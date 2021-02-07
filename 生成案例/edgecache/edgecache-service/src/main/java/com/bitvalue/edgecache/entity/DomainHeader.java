package com.bitvalue.edgecache.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * domainHeader实体类
 * @author wubo
 */
@Entity
@Table(name = "domain_header")
@DynamicInsert(true)
public class DomainHeader implements Serializable{

	/**
	 * Id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "domainId",referencedColumnName = "id",updatable = false,insertable = false)
	private Domain domain;

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	
	/**
 	 * 域名的Id
 	 */
	private Integer domainId;
	/**
 	 * HTTP Header的名称
 	 */
	private String headerKey;
	/**
 	 * HTTP Header的值
 	 */
	private String headerValue;
	/**
 	 * 同步信息:1待同步,2同步成功,3同步失败
 	 */
	private Integer status;

	private Boolean cdn;

	public Boolean getCdn() {
		return cdn;
	}

	public void setCdn(Boolean cdn) {
		this.cdn = cdn;
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

	public String getHeaderKey() {
		return headerKey;
	}
	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}

	public String getHeaderValue() {
		return headerValue;
	}
	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "DomainHeader{" +
				"id=" + id +
				", domainId=" + domainId +
				", headerKey='" + headerKey + '\'' +
				", headerValue='" + headerValue + '\'' +
				", status=" + status +
				", cdn=" + cdn +
				'}';
	}
}

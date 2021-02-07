package com.bitvalue.edgecache.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * wafRule实体类
 * @author wubo
 */
@Entity
@Table(name = "waf_rule")
@DynamicInsert(true)
public class WafRule implements Serializable{

	/*** id */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/*** ruleType */
	private String ruleType;
	/*** ruleItem */
	private String ruleItem;
	/*** 1：待同步 2：同步成功 3：同步失败 */
	private Integer syncStatus;
	/*** 版本号 */
	private String version;

	/** 是否为CDN模式 */
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

	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getRuleItem() {
		return ruleItem;
	}
	public void setRuleItem(String ruleItem) {
		this.ruleItem = ruleItem;
	}

	public Integer getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}

	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}


	
}

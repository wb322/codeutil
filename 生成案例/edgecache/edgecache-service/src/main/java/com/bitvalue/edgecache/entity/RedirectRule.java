package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * redirectRule实体类
 * @author wubo
 */
@Entity
@Table(name = "redirect_rule")
@DynamicInsert(true)
public class RedirectRule implements Serializable{

	/**
	 * id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/**
 	 * 匹配规则
 	 */
	private String rule;
	/**
 	 * 重定向ip
 	 */
	private String ips;

	/**
	 *
	 */
	private String vhost;

	/**
 	 * 类型:dns,http
 	 */
	private String type;


	private Integer rosterType;
	/**
 	 * 修改时间
 	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;

	public Integer getRosterType() {
		return rosterType;
	}

	public void setRosterType(Integer rosterType) {
		this.rosterType = rosterType;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getIps() {
		return ips;
	}

	public void setIps(String ips) {
		this.ips = ips;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getVhost() {
		return vhost;
	}

	public void setVhost(String vhost) {
		this.vhost = vhost;
	}
}

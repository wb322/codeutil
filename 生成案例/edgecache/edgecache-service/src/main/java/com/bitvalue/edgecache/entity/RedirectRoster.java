package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * redirectRoster实体类
 * @author wubo
 */
@Entity
@Table(name = "redirect_roster")
@DynamicInsert(true)
public class RedirectRoster implements Serializable{

	/**
	 * id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/**
 	 * 地址类型:1源地址,2目标地址
 	 */
	private String addr;
	/**
 	 * 值
 	 */
	private String url;
	/**
 	 * 类型:1白名单,2黑名单
 	 */
	private Integer type;
	/**
 	 * 修改时间
 	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}


	
}

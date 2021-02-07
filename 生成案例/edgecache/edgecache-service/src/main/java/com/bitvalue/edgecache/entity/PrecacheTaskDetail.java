package com.bitvalue.edgecache.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * precacheTaskDetail实体类
 * @author wubo
 */
@Entity
@Table(name = "precache_task_detail")
@DynamicInsert(true)
public class PrecacheTaskDetail implements Serializable{

	/*** id */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	/*** 预缓存ID */
	@Column(name = "precache_id")
	private Integer precacheId;
	/*** url */
	@Column(name = "url")
	private String url;
	/*** 状态 */
	@Column(name = "status")
	private String status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrecacheId() {
		return precacheId;
	}
	public void setPrecacheId(Integer precacheId) {
		this.precacheId = precacheId;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}

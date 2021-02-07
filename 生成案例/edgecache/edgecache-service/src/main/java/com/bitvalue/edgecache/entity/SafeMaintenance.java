package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * safeMaintenance实体类
 * @author wb
 */
@Entity
@Table(name = "safe_maintenance")
@DynamicInsert(true)
public class SafeMaintenance implements Serializable{

	/*** id */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/*** IP或网段 */
	@Column(name = "ip")
	private String ip;
	/*** 权限 */
	@Column(name = "auth")
	private String auth;
	/*** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "time")
	private java.util.Date time;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}

	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}


	
}

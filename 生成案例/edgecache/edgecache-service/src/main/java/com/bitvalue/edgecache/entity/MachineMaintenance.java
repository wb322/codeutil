package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * machineMaintenance实体类
 * @author wubo
 */
@Entity
@Table(name = "machine_maintenance")
@DynamicInsert(true)
public class MachineMaintenance implements Serializable{

	/**
	 * id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	/**
 	 * 登录IP
 	 */
	private String ip;
	/**
 	 * 登录端口
 	 */
	private Integer port;
	/**
 	 * 用户名
 	 */
	private String username;
	/**
 	 * 密码
 	 */
	@JsonIgnore
	private String password;
	/**
 	 * 功能角色:dpi,缓存
 	 */
	
	private String role;
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

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}


	
}

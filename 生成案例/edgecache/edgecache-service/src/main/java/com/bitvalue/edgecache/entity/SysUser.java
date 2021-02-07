package com.bitvalue.edgecache.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
/**
 * sysUser实体类
 * @author wubo
 */
@Entity
@Table(name = "sys_user")
@DynamicInsert(true)
public class SysUser implements Serializable{

	/**
	 * id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/**
 	 * 用户名
 	 */
	private String username;
	/**
 	 * 密码
 	 */
	private String password;
	/**
 	 * 是否可用
 	 */
	private Boolean enabled;
	/**
 	 * 说明
 	 */
	private String description;
	/**
 	 * 所属系统
 	 */
	private String sys;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getSys() {
		return sys;
	}
	public void setSys(String sys) {
		this.sys = sys;
	}


	
}

package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
/**
 * sysMenu实体类
 * @author wubo
 */
@Entity
@DynamicInsert(true)
@Table(name="sys_menu")
public class SysMenu implements Serializable{

	/**
	 * ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/**
 	 * 菜单名称
 	 */
	private String name;
	/**
 	 * 菜单url
 	 */
	private String url;
	/**
 	 * 0:分类 1:菜单
 	 */
	private Integer type;
	/**
 	 * 上级ID,0为顶级菜单
 	 */
	private Integer pid;
	/**
 	 * 图标
 	 */
	private String icon;
	/**
 	 * create_time
 	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;

	/**
	 * 状态 0:正常  1：隐藏
	 */
	private Integer status;

	@Formula("(SELECT p.name FROM sys_menu p WHERE p.id = pid)")
	private String parentName;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}


	
}

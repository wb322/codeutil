package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * redirectHeart实体类
 * @author wubo
 */
@Entity
@Table(name = "redirect_heart")
@DynamicInsert(true)
public class RedirectHeart implements Serializable{

	/**
	 * id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/**
 	 * 检测地址
 	 */
	private String url;
	/**
 	 * 检测间隔
 	 */
	private Integer separation;
	/**
 	 * 失败条件
 	 */
	private Integer fail;
	/**
 	 * 恢复条件
 	 */
	private Integer recovery;
	/**
 	 * 是否启用
 	 */
	private Boolean enable;
	/**
 	 * update_time
 	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSeparation() {
		return separation;
	}
	public void setSeparation(Integer separation) {
		this.separation = separation;
	}

	public Integer getFail() {
		return fail;
	}
	public void setFail(Integer fail) {
		this.fail = fail;
	}

	public Integer getRecovery() {
		return recovery;
	}
	public void setRecovery(Integer recovery) {
		this.recovery = recovery;
	}

	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}


	
}

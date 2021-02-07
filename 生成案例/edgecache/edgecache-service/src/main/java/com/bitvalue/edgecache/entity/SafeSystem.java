package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * safeSystem实体类
 * @author wubo
 */
@Entity
@Table(name = "safe_system")
@DynamicInsert(true)
public class SafeSystem implements Serializable{

	/*** ID */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/*** 允许网段 */
	private String networkSegment;
	/*** 说明 */
	private String description;
	/*** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNetworkSegment() {
		return networkSegment;
	}
	public void setNetworkSegment(String networkSegment) {
		this.networkSegment = networkSegment;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}


	
}

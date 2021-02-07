package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * contentRefreshTask实体类
 * @author wubo
 */
@Entity
@Table(name = "content_refresh_task")
@DynamicInsert(true)
public class ContentRefreshTask implements Serializable{

	/*** id */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	/*** 目录刷新 ｜ URL刷新 */
	@Column(name = "refresh_type")
	private String refreshType;
	/*** 刷新时间 */
	@Column(name = "refresh_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date refreshTime;
	/*** 任务个数 */
	@Column(name = "task_count")
	private Integer taskCount;
	/*** 状态 */
	@Column(name = "status")
	private String status;

	private Boolean cdn;

	@OneToMany(mappedBy = "contentId")
	private List<ContentRefreshTaskDetail> details;

	public List<ContentRefreshTaskDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ContentRefreshTaskDetail> details) {
		this.details = details;
	}

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

	public String getRefreshType() {
		return refreshType;
	}
	public void setRefreshType(String refreshType) {
		this.refreshType = refreshType;
	}

	public java.util.Date getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(java.util.Date refreshTime) {
		this.refreshTime = refreshTime;
	}

	public Integer getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	
}

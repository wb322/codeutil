package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * precacheTask实体类
 * @author wubo
 */
@Entity
@Table(name = "precache_task")
@DynamicInsert(true)
public class PrecacheTask implements Serializable{

	/*** id */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/*** 任务个数 */
	@Column(name = "task_count")
	private Integer taskCount;
	/*** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time")
	private java.util.Date createTime;
	/*** 状态 */
	@Column(name = "status")
	private String status;
	/*** 执行结果 */
	@Column(name = "msg")
	private String msg;
	/*** 开始时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "start_time")
	private java.util.Date startTime;
	/*** 结束时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "end_time")
	private java.util.Date endTime;
	private Boolean cdn;

	@OneToMany(mappedBy = "precacheId",fetch = FetchType.EAGER)
	private List<PrecacheTaskDetail> details;

	public List<PrecacheTaskDetail> getDetails() {
		return details;
	}

	public void setDetails(List<PrecacheTaskDetail> details) {
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

	public Integer getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public java.util.Date getStartTime() {
		return startTime;
	}
	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public java.util.Date getEndTime() {
		return endTime;
	}
	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}


	
}

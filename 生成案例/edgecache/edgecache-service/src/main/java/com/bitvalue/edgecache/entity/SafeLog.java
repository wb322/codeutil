package com.bitvalue.edgecache.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * safeLog实体类
 * @author wb
 */
@Entity
@Table(name = "safe_log")
@DynamicInsert(true)
public class SafeLog implements Serializable{

	/*** id */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/*** 用户名 */
	@Column(name = "username")
	private String username;
	/*** 终端 */
	@Column(name = "terminal")
	private String terminal;
	/*** 登录IP或者内核 */
	@Column(name = "connected")
	private String connected;
	/*** 登陆时间 */
	@Column(name = "start")
	private java.util.Date start;
	/*** 结束状态 */
	@Column(name = "end")
	private String end;
	/*** 持续时间 */
	@Column(name = "still")
	private String still;
	/*** 整行 */
	@Column(name = "line")
	private String line;

	private String status;

	private Date time;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public SafeLog() {
	}

	public SafeLog(String line, String status) {
		this.line = line;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getConnected() {
		return connected;
	}
	public void setConnected(String connected) {
		this.connected = connected;
	}

	public java.util.Date getStart() {
		return start;
	}
	public void setStart(java.util.Date start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}

	public String getStill() {
		return still;
	}
	public void setStill(String still) {
		this.still = still;
	}

	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}


	
}

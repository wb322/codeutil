package com.bitvalue.edgecache.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * statistics实体类
 * @author wb
 */
@Entity
@Table(name = "statistics")
@DynamicInsert(true)
public class Statistics implements Serializable{

	/*** id */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/*** http重定向次数 */
	@Column(name = "http")
	private Integer http;
	/*** dns重定向次数 */
	@Column(name = "dns")
	private Integer dns;
	/*** 类型,1:重定向次数 */
	@Column(name = "type")
	private Integer type;
	/*** 时间戳 */
	@Column(name = "time")
	private java.util.Date time;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHttp() {
		return http;
	}
	public void setHttp(Integer http) {
		this.http = http;
	}

	public Integer getDns() {
		return dns;
	}
	public void setDns(Integer dns) {
		this.dns = dns;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}


	
}

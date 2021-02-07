package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
/**
 * sysLog实体类
 * @author wubo
 */
@Entity
@Table(name = "sys_log")
@DynamicInsert(true)
public class SysLog implements Serializable{

	/**
	 * ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	/** 操作用户 */
	private String username;
	/**
 	 * module
 	 */
	private String module;
	/**
 	 * 操作类型
 	 */
	private String type;
	/**
 	 * 匹配的类.方法
 	 */
	private String codeMethod;
	/**
 	 * IP
 	 */
	private String ip;
	/**
 	 * 请求url
 	 */
	private String url;
	/**
 	 * 请求方法
 	 */
	private String httpMethod;
	/**
 	 * 请求参数
 	 */
	private String params;
	/**
 	 * 返回参数
 	 */
	private String result;
	/**
 	 * 操作状态（0正常 1异常）
 	 */
	private Integer status;
	/**
 	 * 日志时间
 	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String time;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getCodeMethod() {
		return codeMethod;
	}
	public void setCodeMethod(String codeMethod) {
		this.codeMethod = codeMethod;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

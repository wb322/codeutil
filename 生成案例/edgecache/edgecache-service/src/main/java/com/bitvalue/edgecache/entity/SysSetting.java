package com.bitvalue.edgecache.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * sysSetting实体类
 * @author wubo
 */
@Entity
@Table(name = "sys_setting")
@DynamicInsert(true)
public class SysSetting implements Serializable{

	/**
	 * id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/**
 	 * 系统名称
 	 */
	private String name;
	/**
 	 * 系统版本
 	 */
	private String version;
	/**
 	 * 控制中心
 	 */
	private String control;
	/**
 	 * License有效期
 	 */
	private String license;
	/**
 	 * 系统安装包
 	 */
	private String pack;
	/**
 	 * 是否为CDN模式
 	 */
	private Boolean cdn;

	
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

	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public String getControl() {
		return control;
	}
	public void setControl(String control) {
		this.control = control;
	}

	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}

	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}

	public Boolean getCdn() {
		return cdn;
	}
	public void setCdn(Boolean cdn) {
		this.cdn = cdn;
	}

}

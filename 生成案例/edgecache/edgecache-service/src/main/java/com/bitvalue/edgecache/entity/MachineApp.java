package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * machineApp实体类
 * @author wubo
 */
@Entity
@Table(name = "machine_app")
@DynamicInsert(true)
public class MachineApp implements Serializable{

	/**
	 * ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;



	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="machineId",nullable=false,insertable=false,updatable=false)
	private MachineMaintenance machineMaintenance;

	public MachineMaintenance getMachineMaintenance() {
		return machineMaintenance;
	}

	public void setMachineMaintenance(MachineMaintenance machineMaintenance) {
		this.machineMaintenance = machineMaintenance;
	}

	/**
 	 * 设备ID
 	 */
	private Integer machineId;
	/**
 	 * DPI系统,0未安装 1安装中 2已安装 3安装失败
 	 */
	private Integer dpi;
	/**
 	 * DPI配置,0未配置 1配置中 2已配置 3配置失败
 	 */
	private Integer dpiConfig;
	/**
 	 * 缓存系统,0未安装 1安装中 2已安装 3安装失败
 	 */
	private Integer cache;
	/**
 	 * 缓存配置,0未配置 1配置中 2已配置 3配置失败
 	 */
	private Integer cacheConfig;
	/**
 	 * WEB管理,0未安装 1安装中 2已安装 3安装失败
 	 */
	private Integer web;
	/**
 	 * WEB配置,0未配置 1配置中 2已配置 3配置失败
 	 */
	private Integer webConfig;
	/**
 	 * 负载均衡,0未安装 1安装中 2已安装 3安装失败
 	 */
	private Integer gslb;
	/**
 	 * 负载均衡配置,0未配置 1配置中 2已配置 3配置失败
 	 */
	private Integer gslbConfig;
	/**
 	 * 状态
 	 */
	private Integer status;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMachineId() {
		return machineId;
	}
	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	public Integer getDpi() {
		return dpi;
	}
	public void setDpi(Integer dpi) {
		this.dpi = dpi;
	}

	public Integer getDpiConfig() {
		return dpiConfig;
	}
	public void setDpiConfig(Integer dpiConfig) {
		this.dpiConfig = dpiConfig;
	}

	public Integer getCache() {
		return cache;
	}
	public void setCache(Integer cache) {
		this.cache = cache;
	}

	public Integer getCacheConfig() {
		return cacheConfig;
	}
	public void setCacheConfig(Integer cacheConfig) {
		this.cacheConfig = cacheConfig;
	}

	public Integer getWeb() {
		return web;
	}
	public void setWeb(Integer web) {
		this.web = web;
	}

	public Integer getWebConfig() {
		return webConfig;
	}
	public void setWebConfig(Integer webConfig) {
		this.webConfig = webConfig;
	}

	public Integer getGslb() {
		return gslb;
	}
	public void setGslb(Integer gslb) {
		this.gslb = gslb;
	}

	public Integer getGslbConfig() {
		return gslbConfig;
	}
	public void setGslbConfig(Integer gslbConfig) {
		this.gslbConfig = gslbConfig;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


	
}

package com.bitvalue.edgecache.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * machineCache实体类
 * @author wubo
 */
@Entity
@Table(name = "machine_cache")
@DynamicInsert(true)
public class MachineCache implements Serializable{

	/*** ID */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/*** 机器ID */
	private Integer machineId;
	/*** 服务IP */
	private String serverIp;
	/*** 服务端口 */
	private String serverPort;
	/*** 安装目录 */
	private String installDir;
	/*** 安装版本 */
	private String installVersion;
	/*** 是否为独立运行模式 */
	private Integer status;
	/*** 是否为默认配置 */
	private Integer defaultConfig;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "machineCache")
	private List<MachineCacheStorage> machineCacheStorageList;

	public List<MachineCacheStorage> getMachineCacheStorageList() {
		return machineCacheStorageList;
	}

	public void setMachineCacheStorageList(List<MachineCacheStorage> machineCacheStorageList) {
		this.machineCacheStorageList = machineCacheStorageList;
	}

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

	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getInstallDir() {
		return installDir;
	}
	public void setInstallDir(String installDir) {
		this.installDir = installDir;
	}

	public String getInstallVersion() {
		return installVersion;
	}
	public void setInstallVersion(String installVersion) {
		this.installVersion = installVersion;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDefaultConfig() {
		return defaultConfig;
	}

	public void setDefaultConfig(Integer defaultConfig) {
		this.defaultConfig = defaultConfig;
	}

	public MachineCache() {
	}
	public MachineCache(Integer machineId,Integer defaultConfig) {
		this.machineId = machineId;
		this.defaultConfig = defaultConfig;
	}
}

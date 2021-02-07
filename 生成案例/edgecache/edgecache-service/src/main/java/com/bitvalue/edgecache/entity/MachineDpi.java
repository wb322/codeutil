package com.bitvalue.edgecache.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * machineDpi实体类
 * @author wubo
 */
@Entity
@Table(name = "machine_dpi")
@DynamicInsert(true)
public class MachineDpi implements Serializable{

	/*** ID */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/*** 机器ID */
	private Integer machineId;
	/*** 接口 */
	private String network;
	/*** 接口状态 */
	private String networkStatus;
	/*** 接口速度 */
	private String networkSpeed;
	/*** 存储服务器 */
	private String storageServer;
	/*** 统计服务器 */
	private String statisticsServer;
	/*** 是否独立运行模式 */
	private Integer status;
	/*** 安装目录 */
	private String installDir;
	/*** 安装版本 */
	private String installVersion;
	/*** 是否为默认配置 */
	private Integer defaultConfig;

	
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

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getNetworkStatus() {
		return networkStatus;
	}

	public void setNetworkStatus(String networkStatus) {
		this.networkStatus = networkStatus;
	}

	public String getNetworkSpeed() {
		return networkSpeed;
	}

	public void setNetworkSpeed(String networkSpeed) {
		this.networkSpeed = networkSpeed;
	}

	public String getStorageServer() {
		return storageServer;
	}
	public void setStorageServer(String storageServer) {
		this.storageServer = storageServer;
	}

	public String getStatisticsServer() {
		return statisticsServer;
	}
	public void setStatisticsServer(String statisticsServer) {
		this.statisticsServer = statisticsServer;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getDefaultConfig() {
		return defaultConfig;
	}

	public void setDefaultConfig(Integer defaultConfig) {
		this.defaultConfig = defaultConfig;
	}

	public MachineDpi(){}

	public MachineDpi(Integer machineId,Integer defaultConfig) {
		this.machineId = machineId;
		this.defaultConfig = defaultConfig;
	}
}

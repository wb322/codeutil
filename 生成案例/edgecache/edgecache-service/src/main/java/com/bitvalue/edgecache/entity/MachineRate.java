package com.bitvalue.edgecache.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * machineRate实体类
 * @author wubo
 */
@Entity
@Table(name = "machine_rate")
@DynamicInsert(true)
public class MachineRate implements Serializable{

	/*** id */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	
	/*** 机器IP */
	@Column(name = "ip")
	private String ip;
	/*** CPU使用率 */
	@Column(name = "cpu")
	private Double cpu;
	/*** 总内存 */
	@Column(name = "memory_total")
	private String memoryTotal;
	/*** 已使用内存 */
	@Column(name = "memory_used")
	private String memoryUsed;
	/*** 内存使用率 */
	@Column(name = "memory_rate")
	private Double memoryRate;
	/*** 总磁盘大小 */
	@Column(name = "disk_total")
	private String diskTotal;
	/*** 已使用磁盘大小 */
	@Column(name = "disk_used")
	private String diskUsed;
	/*** 磁盘使用率 */
	@Column(name = "disk_rate")
	private Double diskRate;
	/*** 时间 */
	@Column(name = "time")
	private java.util.Date time;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public Double getCpu() {
		return cpu;
	}
	public void setCpu(Double cpu) {
		this.cpu = cpu;
	}

	public String getMemoryTotal() {
		return memoryTotal;
	}
	public void setMemoryTotal(String memoryTotal) {
		this.memoryTotal = memoryTotal;
	}

	public String getMemoryUsed() {
		return memoryUsed;
	}
	public void setMemoryUsed(String memoryUsed) {
		this.memoryUsed = memoryUsed;
	}

	public Double getMemoryRate() {
		return memoryRate;
	}
	public void setMemoryRate(Double memoryRate) {
		this.memoryRate = memoryRate;
	}

	public String getDiskTotal() {
		return diskTotal;
	}
	public void setDiskTotal(String diskTotal) {
		this.diskTotal = diskTotal;
	}

	public String getDiskUsed() {
		return diskUsed;
	}
	public void setDiskUsed(String diskUsed) {
		this.diskUsed = diskUsed;
	}

	public Double getDiskRate() {
		return diskRate;
	}
	public void setDiskRate(Double diskRate) {
		this.diskRate = diskRate;
	}

	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "MachineRate{" +
				"id=" + id +
				", ip='" + ip + '\'' +
				", cpu=" + cpu +
				", memoryTotal='" + memoryTotal + '\'' +
				", memoryUsed='" + memoryUsed + '\'' +
				", memoryRate=" + memoryRate +
				", diskTotal='" + diskTotal + '\'' +
				", diskUsed='" + diskUsed + '\'' +
				", diskRate=" + diskRate +
				", time=" + time +
				'}';
	}
}

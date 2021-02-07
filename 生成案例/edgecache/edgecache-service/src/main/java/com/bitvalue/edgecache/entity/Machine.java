package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * machine实体类
 * @author wubo
 */
@Entity
@Table(name = "machine")
@DynamicInsert(true)
public class Machine implements Serializable{

	/**
	 * 设备ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 机器维护ID
	 */
	private Integer machineId;


	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="machineId",nullable=false,insertable=false,updatable=false)
	private MachineMaintenance machineMaintenance;


	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	public MachineMaintenance getMachineMaintenance() {
		return machineMaintenance;
	}

	public void setMachineMaintenance(MachineMaintenance machineMaintenance) {
		this.machineMaintenance = machineMaintenance;
	}

	/**
 	 * 主机名
 	 */
	private String name;
	/**
 	 * 操作系统
 	 */
	private String system;
	/**
 	 * 硬盘
 	 */
	private String disk;
	/**
 	 * 内存
 	 */
	private String memory;
	/**
 	 * CPU
 	 */
	private String cpu;
	/**
 	 * 网卡
 	 */
	private String network;





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

	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}

	public String getDisk() {
		return disk;
	}
	public void setDisk(String disk) {
		this.disk = disk;
	}

	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}


	
}

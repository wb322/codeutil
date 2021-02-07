package com.bitvalue.edgecache.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * machineCacheStorage实体类
 * @author wubo
 */
@Entity
@Table(name = "machine_cache_storage")
@DynamicInsert(true)
public class MachineCacheStorage implements Serializable{

	/*** ID */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/*** 缓存配置ID */
	/*** 存储空间类型 */
	private String type;
	/*** 存储控件位置 */
	private String path;
	/*** 存储空间大小 */
	private String size;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cacheId")
	private MachineCache machineCache;

	public MachineCache getMachineCache() {
		return machineCache;
	}

	public void setMachineCache(MachineCache machineCache) {
		this.machineCache = machineCache;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}


	
}

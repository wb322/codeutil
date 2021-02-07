package com.bitvalue.edgecache.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * contentRefreshTaskDetail实体类
 * @author wubo
 */
@Entity
@Table(name = "content_refresh_task_detail")
@DynamicInsert(true)
public class ContentRefreshTaskDetail implements Serializable{

	/*** id */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;



	/*** 主表ID */
	@Column(name = "contentId")
	private Integer contentId;
	/*** URL或者目录 */
	@Column(name = "urlOrDirectory")
	private String urlOrDirectory;
	/*** 成功 失败 */
	@Column(name = "status")
	private String status;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public String getUrlOrDirectory() {
		return urlOrDirectory;
	}
	public void setUrlOrDirectory(String urlOrDirectory) {
		this.urlOrDirectory = urlOrDirectory;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	
}

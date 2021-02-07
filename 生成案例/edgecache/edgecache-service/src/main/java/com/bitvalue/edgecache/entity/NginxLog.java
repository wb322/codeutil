package com.bitvalue.edgecache.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * nginxLog实体类
 * @author wubo
 */
@Entity
@Table(name = "nginx_log")
@DynamicInsert(true)
public class NginxLog implements Serializable{

	/*** id */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	
	/*** 用户IP */
	@Column(name = "client_ip")
	private String clientIp;
	/*** 时间 */
	@Column(name = "msec")
	private java.util.Date msec;
	/*** 请求协议 */
	@Column(name = "server_protocol")
	private String serverProtocol;
	/*** 域名 */
	@Column(name = "host")
	private String host;
	/*** Http响应状态码 */
	@Column(name = "status")
	private String status;
	/*** 缓存状态码 */
	@Column(name = "cache_code")
	private String cacheCode;
	/*** 响应大小 */
	@Column(name = "bytes_sent")
	private String bytesSent;
	/*** HTTP方法 */
	@Column(name = "request_method")
	private String requestMethod;
	/*** 请求URL */
	@Column(name = "url")
	private String url;
	/*** 后端信息 */
	@Column(name = "upstream_addr")
	private String upstreamAddr;
	/*** 后端HTTP状态码 */
	@Column(name = "upstream_status")
	private String upstreamStatus;
	/*** 请求大小 */
	@Column(name = "rbsize")
	private String rbsize;
	/*** 上游缓存状态码 */
	@Column(name = "hier")
	private String hier;
	/*** 服务IP */
	@Column(name = "server_addr")
	private String serverAddr;
	/*** 内容类型 */
	@Column(name = "content_type")
	private String contentType;
	/*** Refer */
	@Column(name = "http_referer")
	private String httpReferer;
	/*** 客户端类型 */
	@Column(name = "http_user_agent")
	private String httpUserAgent;
	/*** 下载时长 */
	@Column(name = "request_time")
	private String requestTime;
	/*** 上游IP */
	@Column(name = "parent_ip")
	private String parentIp;
	/*** COOKIE */
	@Column(name = "http_cookie")
	private String httpCookie;
	/*** 分片 */
	@Column(name = "http_range")
	private String httpRange;
	/*** 上游HTTP状态码 */
	@Column(name = "parent_resp_code")
	private String parentRespCode;
	/*** 请求ID */
	@Column(name = "requestid")
	private String requestid;
	/*** 事务完成状态 */
	@Column(name = "request_completion")
	private String requestCompletion;
	/*** 跳数 */
	@Column(name = "request_hop")
	private Integer requestHop;
	/*** 下游IP */
	@Column(name = "remote_addr")
	private String remoteAddr;
	/*** 防护类型 */
	@Column(name = "deny_type")
	private Integer denyType;
	/*** 响应头-文件大小 */
	@Column(name = "clength")
	private String clength;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public java.util.Date getMsec() {
		return msec;
	}
	public void setMsec(java.util.Date msec) {
		this.msec = msec;
	}

	public String getServerProtocol() {
		return serverProtocol;
	}
	public void setServerProtocol(String serverProtocol) {
		this.serverProtocol = serverProtocol;
	}

	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getCacheCode() {
		return cacheCode;
	}
	public void setCacheCode(String cacheCode) {
		this.cacheCode = cacheCode;
	}

	public String getBytesSent() {
		return bytesSent;
	}
	public void setBytesSent(String bytesSent) {
		this.bytesSent = bytesSent;
	}

	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getUpstreamAddr() {
		return upstreamAddr;
	}
	public void setUpstreamAddr(String upstreamAddr) {
		this.upstreamAddr = upstreamAddr;
	}

	public String getUpstreamStatus() {
		return upstreamStatus;
	}
	public void setUpstreamStatus(String upstreamStatus) {
		this.upstreamStatus = upstreamStatus;
	}

	public String getRbsize() {
		return rbsize;
	}
	public void setRbsize(String rbsize) {
		this.rbsize = rbsize;
	}

	public String getHier() {
		return hier;
	}
	public void setHier(String hier) {
		this.hier = hier;
	}

	public String getServerAddr() {
		return serverAddr;
	}
	public void setServerAddr(String serverAddr) {
		this.serverAddr = serverAddr;
	}

	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getHttpReferer() {
		return httpReferer;
	}
	public void setHttpReferer(String httpReferer) {
		this.httpReferer = httpReferer;
	}

	public String getHttpUserAgent() {
		return httpUserAgent;
	}
	public void setHttpUserAgent(String httpUserAgent) {
		this.httpUserAgent = httpUserAgent;
	}

	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getParentIp() {
		return parentIp;
	}
	public void setParentIp(String parentIp) {
		this.parentIp = parentIp;
	}

	public String getHttpCookie() {
		return httpCookie;
	}
	public void setHttpCookie(String httpCookie) {
		this.httpCookie = httpCookie;
	}

	public String getHttpRange() {
		return httpRange;
	}
	public void setHttpRange(String httpRange) {
		this.httpRange = httpRange;
	}

	public String getParentRespCode() {
		return parentRespCode;
	}
	public void setParentRespCode(String parentRespCode) {
		this.parentRespCode = parentRespCode;
	}

	public String getRequestid() {
		return requestid;
	}
	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}

	public String getRequestCompletion() {
		return requestCompletion;
	}
	public void setRequestCompletion(String requestCompletion) {
		this.requestCompletion = requestCompletion;
	}

	public Integer getRequestHop() {
		return requestHop;
	}
	public void setRequestHop(Integer requestHop) {
		this.requestHop = requestHop;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public Integer getDenyType() {
		return denyType;
	}
	public void setDenyType(Integer denyType) {
		this.denyType = denyType;
	}

	public String getClength() {
		return clength;
	}
	public void setClength(String clength) {
		this.clength = clength;
	}


	
}

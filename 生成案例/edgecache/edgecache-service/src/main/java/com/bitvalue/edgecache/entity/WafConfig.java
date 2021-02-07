package com.bitvalue.edgecache.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
/**
 * wafConfig实体类
 * @author wubo
 */
@Entity
@Table(name = "waf_config")
@DynamicInsert(true)
public class WafConfig implements Serializable{

	/*** id */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	
	/*** config_waf_enable */
	private String configWafEnable;
	/*** config_white_url_check */
	private String configWhiteUrlCheck;
	/*** config_white_ip_check */
	private String configWhiteIpCheck;
	/*** config_black_ip_check */
	private String configBlackIpCheck;
	/*** config_url_check */
	private String configUrlCheck;
	/*** config_url_args_check */
	private String configUrlArgsCheck;
	/*** config_user_agent_check */
	private String configUserAgentCheck;
	/*** config_cookie_check */
	private String configCookieCheck;
	/*** config_cc_check */
	private String configCcCheck;
	/*** config_cc_rate */
	private String configCcRate;
	/*** config_post_check */
	private String configPostCheck;
	/*** config_waf_model */
	private String configWafModel;
	/*** config_waf_redirect_url */
	private String configWafRedirectUrl;
	/*** config_output_html */
	private String configOutputHtml;
	/*** config_log_dir */
	private String configLogDir;
	/*** config_rule_dir */
	private String configRuleDir;
	/*** 1：待同步 2：同步成功 3：同步失败 */
	private Integer syncStatus;
	/*** 版本号 */
	private String version;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getConfigWafEnable() {
		return configWafEnable;
	}
	public void setConfigWafEnable(String configWafEnable) {
		this.configWafEnable = configWafEnable;
	}

	public String getConfigWhiteUrlCheck() {
		return configWhiteUrlCheck;
	}
	public void setConfigWhiteUrlCheck(String configWhiteUrlCheck) {
		this.configWhiteUrlCheck = configWhiteUrlCheck;
	}

	public String getConfigWhiteIpCheck() {
		return configWhiteIpCheck;
	}
	public void setConfigWhiteIpCheck(String configWhiteIpCheck) {
		this.configWhiteIpCheck = configWhiteIpCheck;
	}

	public String getConfigBlackIpCheck() {
		return configBlackIpCheck;
	}
	public void setConfigBlackIpCheck(String configBlackIpCheck) {
		this.configBlackIpCheck = configBlackIpCheck;
	}

	public String getConfigUrlCheck() {
		return configUrlCheck;
	}
	public void setConfigUrlCheck(String configUrlCheck) {
		this.configUrlCheck = configUrlCheck;
	}

	public String getConfigUrlArgsCheck() {
		return configUrlArgsCheck;
	}
	public void setConfigUrlArgsCheck(String configUrlArgsCheck) {
		this.configUrlArgsCheck = configUrlArgsCheck;
	}

	public String getConfigUserAgentCheck() {
		return configUserAgentCheck;
	}
	public void setConfigUserAgentCheck(String configUserAgentCheck) {
		this.configUserAgentCheck = configUserAgentCheck;
	}

	public String getConfigCookieCheck() {
		return configCookieCheck;
	}
	public void setConfigCookieCheck(String configCookieCheck) {
		this.configCookieCheck = configCookieCheck;
	}

	public String getConfigCcCheck() {
		return configCcCheck;
	}
	public void setConfigCcCheck(String configCcCheck) {
		this.configCcCheck = configCcCheck;
	}

	public String getConfigCcRate() {
		return configCcRate;
	}
	public void setConfigCcRate(String configCcRate) {
		this.configCcRate = configCcRate;
	}

	public String getConfigPostCheck() {
		return configPostCheck;
	}
	public void setConfigPostCheck(String configPostCheck) {
		this.configPostCheck = configPostCheck;
	}

	public String getConfigWafModel() {
		return configWafModel;
	}
	public void setConfigWafModel(String configWafModel) {
		this.configWafModel = configWafModel;
	}

	public String getConfigWafRedirectUrl() {
		return configWafRedirectUrl;
	}
	public void setConfigWafRedirectUrl(String configWafRedirectUrl) {
		this.configWafRedirectUrl = configWafRedirectUrl;
	}

	public String getConfigOutputHtml() {
		return configOutputHtml;
	}
	public void setConfigOutputHtml(String configOutputHtml) {
		this.configOutputHtml = configOutputHtml;
	}

	public String getConfigLogDir() {
		return configLogDir;
	}
	public void setConfigLogDir(String configLogDir) {
		this.configLogDir = configLogDir;
	}

	public String getConfigRuleDir() {
		return configRuleDir;
	}
	public void setConfigRuleDir(String configRuleDir) {
		this.configRuleDir = configRuleDir;
	}

	public Integer getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}

	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}


	
}

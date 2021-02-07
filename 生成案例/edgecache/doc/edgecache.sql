/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.31 : Database - edgecache
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`edgecache` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `edgecache`;

/*Table structure for table `content_refresh_task` */

DROP TABLE IF EXISTS `content_refresh_task`;

CREATE TABLE `content_refresh_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `refresh_type` varchar(30) DEFAULT NULL COMMENT '目录刷新 ｜ URL刷新',
  `refresh_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '刷新时间',
  `task_count` int(11) DEFAULT NULL COMMENT '任务个数',
  `status` varchar(30) DEFAULT '等待执行' COMMENT '状态',
  `cdn` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;

/*Table structure for table `content_refresh_task_detail` */

DROP TABLE IF EXISTS `content_refresh_task_detail`;

CREATE TABLE `content_refresh_task_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content_id` int(11) DEFAULT NULL COMMENT '主表ID',
  `url_or_directory` varchar(200) DEFAULT NULL COMMENT 'URL或者目录',
  `status` varchar(30) DEFAULT NULL COMMENT '成功 失败',
  PRIMARY KEY (`id`),
  KEY `contentId` (`content_id`),
  CONSTRAINT `contentId` FOREIGN KEY (`content_id`) REFERENCES `content_refresh_task` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8;

/*Table structure for table `domain` */

DROP TABLE IF EXISTS `domain`;

CREATE TABLE `domain` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `url` varchar(50) DEFAULT NULL COMMENT '域名',
  `source_url` text COMMENT '源地址',
  `return_source_type` tinyint(1) DEFAULT NULL COMMENT '回源方式:1主备,2轮询',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(1) DEFAULT NULL COMMENT '同步状态',
  `cdn` tinyint(1) DEFAULT '0' COMMENT 'CDN模式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Table structure for table `domain_cache` */

DROP TABLE IF EXISTS `domain_cache`;

CREATE TABLE `domain_cache` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `domain_id` int(11) DEFAULT NULL COMMENT '域名ID',
  `cache_type` int(30) DEFAULT NULL COMMENT '1：文件类型 2：文件夹 3：全路径文件 4：首页',
  `cache_content` varchar(200) DEFAULT NULL COMMENT '缓存的内容',
  `cache_time` int(11) DEFAULT NULL COMMENT '缓存时间',
  `limit_time` varchar(30) DEFAULT NULL COMMENT 'd 天、h小时、m分、s秒',
  `weight` int(11) DEFAULT NULL COMMENT '优先级 数字越大越优先',
  `status` int(11) DEFAULT NULL COMMENT '同步状态 1：待同步 2：同步成功 3：同步失败',
  `cdn` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `domain_cache_refresh` */

DROP TABLE IF EXISTS `domain_cache_refresh`;

CREATE TABLE `domain_cache_refresh` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `type` tinyint(1) DEFAULT NULL COMMENT '类型,1:预缓存 2:内容刷新',
  `start` datetime DEFAULT NULL COMMENT '开始时间',
  `end` datetime DEFAULT NULL COMMENT '结束时间',
  `content` text COMMENT '内容',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `cdn` tinyint(1) DEFAULT '0' COMMENT '是否为CDN模式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `domain_cache_refresh` */

/*Table structure for table `domain_header` */

DROP TABLE IF EXISTS `domain_header`;

CREATE TABLE `domain_header` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `domain_id` int(11) DEFAULT NULL COMMENT '域名的Id',
  `header_key` varchar(30) DEFAULT 'null' COMMENT 'HTTP Header的名称',
  `header_value` varchar(30) DEFAULT 'null' COMMENT 'HTTP Header的值',
  `status` int(11) DEFAULT '1' COMMENT '同步信息:1待同步,2同步成功,3同步失败',
  `cdn` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `domain_header` */

/*Table structure for table `machine` */

DROP TABLE IF EXISTS `machine`;

CREATE TABLE `machine` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `machine_id` int(10) DEFAULT NULL COMMENT '机器维护ID',
  `name` varchar(256) DEFAULT NULL COMMENT '主机名',
  `system` varchar(256) DEFAULT NULL COMMENT '操作系统',
  `disk` varchar(20) DEFAULT NULL COMMENT '硬盘',
  `memory` varchar(20) DEFAULT NULL COMMENT '内存',
  `cpu` varchar(30) DEFAULT NULL COMMENT 'CPU',
  `network` varchar(256) DEFAULT NULL COMMENT '网卡',
  PRIMARY KEY (`id`),
  KEY `machine_info_fk` (`machine_id`),
  CONSTRAINT `machine_info_fk` FOREIGN KEY (`machine_id`) REFERENCES `machine_maintenance` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `machine_app` */

DROP TABLE IF EXISTS `machine_app`;

CREATE TABLE `machine_app` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `machine_id` int(10) NOT NULL COMMENT '设备ID',
  `dpi` tinyint(1) DEFAULT '0' COMMENT 'DPI系统  0:未安装,1:已安装,2:安装失败',
  `dpi_config` tinyint(1) DEFAULT '0' COMMENT 'DPI配置',
  `cache` tinyint(1) DEFAULT '0' COMMENT '缓存系统',
  `cache_config` tinyint(1) DEFAULT '0' COMMENT '缓存配置',
  `web` tinyint(1) DEFAULT '0' COMMENT 'WEB管理',
  `web_config` tinyint(1) DEFAULT '0' COMMENT 'WEB配置',
  `gslb` tinyint(1) DEFAULT '0' COMMENT '负载均衡',
  `gslb_config` tinyint(1) DEFAULT '0' COMMENT '负载均衡配置',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态 0未知，1正常，2异常',
  PRIMARY KEY (`id`),
  KEY `machine_app_fk` (`machine_id`),
  CONSTRAINT `machine_app_fk` FOREIGN KEY (`machine_id`) REFERENCES `machine_maintenance` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `machine_app` */

/*Table structure for table `machine_cache` */

DROP TABLE IF EXISTS `machine_cache`;

CREATE TABLE `machine_cache` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `machine_id` int(10) DEFAULT NULL COMMENT '机器ID',
  `server_ip` varchar(15) DEFAULT NULL COMMENT '服务IP',
  `server_port` varchar(6) DEFAULT NULL COMMENT '服务端口',
  `install_dir` varchar(256) DEFAULT NULL COMMENT '安装目录',
  `install_version` varchar(256) DEFAULT NULL COMMENT '安装版本',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否为独立运行模式',
  `default_config` tinyint(1) DEFAULT NULL COMMENT '是否为默认配置',
  PRIMARY KEY (`id`),
  KEY `machine_cache_fk` (`machine_id`),
  CONSTRAINT `machine_cache_fk` FOREIGN KEY (`machine_id`) REFERENCES `machine_maintenance` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `machine_cache` */

/*Table structure for table `machine_cache_storage` */

DROP TABLE IF EXISTS `machine_cache_storage`;

CREATE TABLE `machine_cache_storage` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `cache_id` int(10) DEFAULT NULL COMMENT '缓存配置ID',
  `type` varchar(128) DEFAULT NULL COMMENT '存储空间类型',
  `path` varchar(128) DEFAULT NULL COMMENT '存储空间位置',
  `size` varchar(50) DEFAULT NULL COMMENT '存储空间大小',
  PRIMARY KEY (`id`),
  KEY `machine_cache_storage_fk` (`cache_id`),
  CONSTRAINT `machine_cache_storage_fk` FOREIGN KEY (`cache_id`) REFERENCES `machine_cache` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `machine_cache_storage` */

/*Table structure for table `machine_dpi` */

DROP TABLE IF EXISTS `machine_dpi`;

CREATE TABLE `machine_dpi` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `machine_id` int(10) DEFAULT NULL COMMENT '机器ID',
  `network` varchar(256) DEFAULT NULL COMMENT '接口',
  `network_status` varchar(256) DEFAULT NULL COMMENT '接口状态',
  `network_speed` varchar(256) DEFAULT NULL COMMENT '接口速度',
  `storage_server` varchar(256) DEFAULT NULL COMMENT '存储服务器',
  `statistics_server` varchar(256) DEFAULT NULL COMMENT '统计服务器',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否独立运行模式',
  `install_dir` varchar(256) DEFAULT NULL COMMENT '安装目录',
  `install_version` varchar(256) DEFAULT NULL COMMENT '安装版本',
  `default_config` tinyint(1) DEFAULT NULL COMMENT '是否为默认配置',
  PRIMARY KEY (`id`),
  KEY `machine_dpi_fk` (`machine_id`),
  CONSTRAINT `machine_dpi_fk` FOREIGN KEY (`machine_id`) REFERENCES `machine_maintenance` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `machine_dpi` */

/*Table structure for table `machine_maintenance` */

DROP TABLE IF EXISTS `machine_maintenance`;

CREATE TABLE `machine_maintenance` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `ip` varchar(15) DEFAULT NULL COMMENT '登录IP',
  `port` int(5) DEFAULT NULL COMMENT '登录端口',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(30) DEFAULT NULL COMMENT '密码',
  `role` varchar(10) DEFAULT NULL COMMENT '功能角色:dpi,缓存',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `machine_maintenance` */

/*Table structure for table `machine_rate` */

DROP TABLE IF EXISTS `machine_rate`;

CREATE TABLE `machine_rate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(15) DEFAULT NULL COMMENT '机器IP',
  `cpu` double(10,2) DEFAULT NULL COMMENT 'CPU使用率',
  `memory_total` varchar(20) DEFAULT NULL COMMENT '总内存',
  `memory_used` varchar(20) DEFAULT NULL COMMENT '已使用内存',
  `memory_rate` double(10,2) DEFAULT NULL COMMENT '内存使用率',
  `disk_total` varchar(20) DEFAULT NULL COMMENT '总磁盘大小',
  `disk_used` varchar(20) DEFAULT NULL COMMENT '已使用磁盘大小',
  `disk_rate` double(10,2) DEFAULT NULL COMMENT '磁盘使用率',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11607 DEFAULT CHARSET=utf8;

/*Data for the table `machine_rate` */

/*Table structure for table `nginx_log` */

DROP TABLE IF EXISTS `nginx_log`;

CREATE TABLE `nginx_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_ip` varchar(255) DEFAULT NULL COMMENT '用户IP',
  `msec` timestamp NULL DEFAULT NULL COMMENT '时间',
  `server_protocol` varchar(255) DEFAULT NULL COMMENT '请求协议',
  `host` varchar(255) DEFAULT NULL COMMENT '域名',
  `status` varchar(255) DEFAULT NULL COMMENT 'Http响应状态码',
  `cache_code` varchar(255) DEFAULT NULL COMMENT '缓存状态码',
  `bytes_sent` varchar(255) DEFAULT NULL COMMENT '响应大小',
  `request_method` varchar(255) DEFAULT NULL COMMENT 'HTTP方法',
  `url` varchar(255) DEFAULT NULL COMMENT '请求URL',
  `upstream_addr` varchar(255) DEFAULT NULL COMMENT '后端信息',
  `upstream_status` varchar(255) DEFAULT NULL COMMENT '后端HTTP状态码',
  `rbsize` varchar(255) DEFAULT NULL COMMENT '请求大小',
  `hier` varchar(255) DEFAULT NULL COMMENT '上游缓存状态码',
  `server_addr` varchar(255) DEFAULT NULL COMMENT '服务IP',
  `content_type` varchar(255) DEFAULT NULL COMMENT '内容类型',
  `http_referer` varchar(255) DEFAULT NULL COMMENT 'Refer',
  `http_user_agent` text COMMENT '客户端类型',
  `request_time` varchar(255) DEFAULT NULL COMMENT '下载时长',
  `parent_ip` varchar(255) DEFAULT NULL COMMENT '上游IP',
  `http_cookie` text COMMENT 'COOKIE',
  `http_range` varchar(255) DEFAULT NULL COMMENT '分片',
  `parent_resp_code` varchar(255) DEFAULT NULL COMMENT '上游HTTP状态码',
  `requestid` varchar(255) DEFAULT NULL COMMENT '请求ID',
  `request_completion` varchar(255) DEFAULT NULL COMMENT '事务完成状态',
  `request_hop` int(5) DEFAULT NULL COMMENT '跳数',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '下游IP',
  `deny_type` int(1) DEFAULT NULL COMMENT '防护类型',
  `clength` varchar(255) DEFAULT NULL COMMENT '响应头-文件大小',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4959 DEFAULT CHARSET=utf8;

/*Data for the table `nginx_log` */

/*Table structure for table `precache_task` */

DROP TABLE IF EXISTS `precache_task`;

CREATE TABLE `precache_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_count` int(11) DEFAULT NULL COMMENT '任务个数',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` varchar(30) DEFAULT '等待执行' COMMENT '状态',
  `msg` varchar(1000) DEFAULT NULL COMMENT '执行结果',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `cdn` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

/*Data for the table `precache_task` */

/*Table structure for table `precache_task_detail` */

DROP TABLE IF EXISTS `precache_task_detail`;

CREATE TABLE `precache_task_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `precache_id` int(11) DEFAULT NULL COMMENT '预缓存ID',
  `url` varchar(500) DEFAULT NULL COMMENT 'url',
  `status` varchar(30) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `precacheid` (`precache_id`),
  CONSTRAINT `precacheid` FOREIGN KEY (`precache_id`) REFERENCES `precache_task` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6175 DEFAULT CHARSET=utf8;

/*Data for the table `precache_task_detail` */

/*Table structure for table `redirect_heart` */

DROP TABLE IF EXISTS `redirect_heart`;

CREATE TABLE `redirect_heart` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `url` varchar(50) DEFAULT NULL COMMENT '检测地址',
  `separation` int(10) DEFAULT NULL COMMENT '检测间隔',
  `fail` int(4) DEFAULT NULL COMMENT '失败条件',
  `recovery` int(4) DEFAULT NULL COMMENT '恢复条件',
  `enable` tinyint(1) DEFAULT NULL COMMENT '是否启用',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `redirect_heart` */

/*Table structure for table `redirect_roster` */

DROP TABLE IF EXISTS `redirect_roster`;

CREATE TABLE `redirect_roster` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `addr` varchar(10) DEFAULT NULL COMMENT '地址类型:1源地址,2目标地址',
  `url` varchar(50) DEFAULT NULL COMMENT '值',
  `type` tinyint(1) DEFAULT NULL COMMENT '类型:1白名单,2黑名单',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `redirect_roster` */

/*Table structure for table `redirect_rule` */

DROP TABLE IF EXISTS `redirect_rule`;

CREATE TABLE `redirect_rule` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `rule` varchar(50) DEFAULT NULL COMMENT '匹配规则',
  `vhost` varchar(50) DEFAULT NULL,
  `ips` varchar(50) DEFAULT NULL COMMENT '重定向ip',
  `type` varchar(10) DEFAULT NULL COMMENT '类型:DNS,HTTP',
  `roster_type` tinyint(1) DEFAULT NULL COMMENT '类型:1白名单,2黑名单',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

/*Data for the table `redirect_rule` */

/*Table structure for table `safe_log` */

DROP TABLE IF EXISTS `safe_log`;

CREATE TABLE `safe_log` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `terminal` varchar(20) DEFAULT NULL COMMENT '终端',
  `connected` varchar(30) DEFAULT NULL COMMENT '登录IP或者内核',
  `start` datetime DEFAULT NULL COMMENT '登陆时间',
  `end` varchar(30) DEFAULT NULL COMMENT '结束状态',
  `still` varchar(30) DEFAULT NULL COMMENT '持续时间',
  `line` varchar(255) DEFAULT NULL COMMENT '整行',
  `status` varchar(10) DEFAULT NULL COMMENT '日志状态',
  `time` timestamp NULL DEFAULT NULL COMMENT '时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2884 DEFAULT CHARSET=utf8;

/*Data for the table `safe_log` */

/*Table structure for table `safe_maintenance` */

DROP TABLE IF EXISTS `safe_maintenance`;

CREATE TABLE `safe_maintenance` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `ip` varchar(30) DEFAULT NULL COMMENT 'IP或网段',
  `auth` varchar(10) DEFAULT NULL COMMENT '权限',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `safe_maintenance` */

/*Table structure for table `safe_system` */

DROP TABLE IF EXISTS `safe_system`;

CREATE TABLE `safe_system` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `network_segment` varchar(20) DEFAULT NULL COMMENT '允许网段',
  `description` varchar(50) DEFAULT NULL COMMENT '说明',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `safe_system` */

/*Table structure for table `statistics` */

DROP TABLE IF EXISTS `statistics`;

CREATE TABLE `statistics` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `http` int(8) DEFAULT NULL COMMENT 'http重定向次数',
  `dns` int(8) DEFAULT NULL COMMENT 'dns重定向次数',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型,1:重定向次数',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `statistics` */

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(50) DEFAULT NULL COMMENT '操作用户',
  `module` varchar(50) DEFAULT NULL COMMENT 'module',
  `type` varchar(10) DEFAULT NULL COMMENT '操作类型',
  `code_method` varchar(255) DEFAULT NULL COMMENT '匹配的类.方法',
  `ip` varchar(15) DEFAULT NULL COMMENT 'IP',
  `url` varchar(50) DEFAULT NULL COMMENT '请求url',
  `http_method` varchar(10) DEFAULT NULL COMMENT '请求方法',
  `params` text COMMENT '请求参数',
  `result` text COMMENT '返回参数',
  `status` int(1) DEFAULT NULL COMMENT '操作状态（0正常 1异常）',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '日志时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=222 DEFAULT CHARSET=utf8;

/*Data for the table `sys_log` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(20) NOT NULL COMMENT '菜单名称',
  `url` varchar(50) DEFAULT NULL COMMENT '菜单url',
  `type` int(1) DEFAULT '0' COMMENT '0:分类 1:菜单',
  `pid` int(10) DEFAULT NULL COMMENT '上级ID,0为顶级菜单',
  `icon` varchar(30) DEFAULT NULL COMMENT '图标',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) DEFAULT '0' COMMENT '状态 0:正常  1：隐藏',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`url`,`type`,`pid`,`icon`,`create_time`,`status`) values (1,'设备管理',NULL,0,NULL,'layui-icon-tabs','2020-06-01 16:55:36',0),(2,'重定向管理',NULL,0,NULL,'layui-icon-next','2020-06-02 16:55:34',0),(3,'内容管理',NULL,0,NULL,'layui-icon-website','2020-06-01 16:55:39',0),(4,'数据分析',NULL,0,NULL,'layui-icon-chart','2020-06-01 16:55:44',0),(5,'问题定位',NULL,0,NULL,'layui-icon-console','2020-06-01 16:55:41',0),(6,'设置',NULL,0,NULL,'layui-icon-set-sm','2020-06-01 16:55:46',0),(7,'安全管理',NULL,0,NULL,'layui-icon-vercode','2020-06-01 16:55:48',0),(8,'设备维护','/menu/machine_maintenance/list',1,1,'','2020-07-09 17:03:28',0),(9,'基础信息','/menu/machine/list',1,1,'','2020-06-01 16:55:58',0),(10,'应用状态','/menu/machine_app/list',1,1,'','2020-06-01 16:55:56',0),(11,'规则管理','/menu/redirect_rule/list',1,2,'','2020-06-01 16:56:00',0),(12,'黑白名单','/menu/redirect_roster/list',1,2,'','2020-06-01 16:56:03',0),(13,'心跳管理','/menu/redirect_heart/list',1,2,'','2020-06-01 16:56:06',1),(14,'域名管理','/menu/domain/list',1,3,'','2020-06-01 16:56:08',0),(15,'域名缓存管理','/menu/domain_cache/list',1,3,'','2020-06-01 16:56:11',0),(16,'HTTP头管理','/menu/domain_header/list',1,3,'','2020-06-01 16:56:14',0),(17,'服务分析','/menu/analysis/server',1,4,'','2020-06-01 16:56:19',0),(18,'监控分析','/menu/analysis/monitor',1,4,'','2020-06-01 16:56:16',0),(19,'自动分析','/question/analysis',1,5,'','2020-06-01 16:56:22',0),(20,'调试工具','/menu/question_dealtool/list',1,5,'','2020-06-01 16:56:25',0),(21,'系统设置','/menu/sys_setting/form',1,6,'','2020-06-01 16:56:27',0),(22,'用户设置','/menu/sys_user/list',1,6,'','2020-06-01 16:56:30',0),(23,'管理系统安全','/menu/safe_system/list',1,7,'','2020-06-01 16:56:32',0),(24,'应用层防火墙','/menu/waf_config/list',1,7,'','2020-06-01 16:56:35',0),(25,'维护安全规则','/menu/safe_maintenance/list',1,7,'','2020-06-01 16:56:37',0),(26,'菜单设置','/menu/sys_menu/list',1,6,NULL,'2020-06-01 16:56:39',0),(27,'内容刷新','/menu/content_refresh_task/list',1,3,'','2020-06-18 11:29:54',0),(28,'预缓存','/menu/precache_task/list',1,3,'','2020-06-18 11:53:58',0);

/*Table structure for table `sys_setting` */

DROP TABLE IF EXISTS `sys_setting`;

CREATE TABLE `sys_setting` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '系统名称',
  `version` varchar(128) DEFAULT NULL COMMENT '系统版本',
  `control` varchar(128) DEFAULT NULL COMMENT '控制中心',
  `license` varchar(128) DEFAULT NULL COMMENT 'License有效期',
  `pack` varchar(128) DEFAULT NULL COMMENT '系统安装包',
  `cdn` tinyint(1) DEFAULT NULL COMMENT '是否为CDN模式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_setting` */

insert  into `sys_setting`(`id`,`name`,`version`,`control`,`license`,`pack`,`cdn`) values (1,'BV-EdgeCache ','V3','192.168.2.1:4046','无','edge-code.v6.0',0);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(36) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT '123456' COMMENT '密码',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否可用',
  `description` varchar(255) DEFAULT '' COMMENT '说明',
  `sys` varchar(10) DEFAULT NULL COMMENT '所属系统',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2147483647 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`enabled`,`description`,`sys`) values (1,'admin','e10adc3949ba59abbe56e057f20f883e',1,'超级管理员，不可修改','超级管理员');

/*Table structure for table `waf_config` */

DROP TABLE IF EXISTS `waf_config`;

CREATE TABLE `waf_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `config_waf_enable` varchar(4) DEFAULT 'off',
  `config_white_url_check` varchar(4) DEFAULT 'on',
  `config_white_ip_check` varchar(4) DEFAULT 'on',
  `config_black_ip_check` varchar(4) DEFAULT 'on',
  `config_url_check` varchar(4) DEFAULT 'on',
  `config_url_args_check` varchar(4) DEFAULT 'on',
  `config_user_agent_check` varchar(4) DEFAULT 'on',
  `config_cookie_check` varchar(4) DEFAULT 'on',
  `config_cc_check` varchar(4) DEFAULT 'on',
  `config_cc_rate` varchar(32) DEFAULT '10/60',
  `config_post_check` varchar(4) DEFAULT 'on',
  `config_waf_model` varchar(8) DEFAULT 'html',
  `config_waf_redirect_url` varchar(1000) DEFAULT 'http://waf.bitvalue.com.cn',
  `config_output_html` varchar(1000) DEFAULT '<html><head><meta charset=''UTF-8''><title>MIDUN WAF</title></head><body><div><div class=''table''><div><div class=''cell''>您的IP为: %s</div><div class=''cell''>欢迎在遵守白帽子道德准则的情况下进行安全测试。</div><div class=''cell''>联系方式：http://waf.test.com</div></div></div></div></body></html>',
  `config_log_dir` varchar(64) DEFAULT '/home/bv/bengine/logs/waf?logs',
  `config_rule_dir` varchar(64) DEFAULT '/home/bv/bengine/conf/x-waf/rules',
  `sync_status` int(11) DEFAULT '1' COMMENT '1：待同步 2：同步成功 3：同步失败',
  `version` varchar(50) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `waf_config` */

insert  into `waf_config`(`id`,`config_waf_enable`,`config_white_url_check`,`config_white_ip_check`,`config_black_ip_check`,`config_url_check`,`config_url_args_check`,`config_user_agent_check`,`config_cookie_check`,`config_cc_check`,`config_cc_rate`,`config_post_check`,`config_waf_model`,`config_waf_redirect_url`,`config_output_html`,`config_log_dir`,`config_rule_dir`,`sync_status`,`version`) values (1,'on','on','on','on','on','on','on','on','on','10/60','on','html','http://www.bitvalue.com/index.html','您的IP为: %s您的非法访问已被拒绝，请联系管理员。联系方式:http://waf.bitvalue.com.cn','/tmp','/home/bv/bengine/conf/x-waf/rules',2,'84e300ad552c4408bb1c9d5115dae44c');

/*Table structure for table `waf_rule` */

DROP TABLE IF EXISTS `waf_rule`;

CREATE TABLE `waf_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rule_type` varchar(32) DEFAULT NULL,
  `rule_item` varchar(256) DEFAULT NULL,
  `sync_status` int(11) DEFAULT '1' COMMENT '1：待同步 2：同步成功 3：同步失败',
  `version` varchar(50) DEFAULT NULL COMMENT '版本号',
  `cdn` tinyint(1) DEFAULT NULL COMMENT '是否为cdn模式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

/*Data for the table `waf_rule` */

/* Trigger structure for table `machine_maintenance` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `insert_machine_info_app` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `insert_machine_info_app` AFTER INSERT ON `machine_maintenance` FOR EACH ROW BEGIN
  DECLARE i INT(10);
  SET i = new.id;
  INSERT INTO machine (machine_id) VALUES(i);
  INSERT INTO machine_app (machine_id) VALUES(i);
END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.23-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.2.0.4949
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 authority-control 的数据库结构
CREATE DATABASE IF NOT EXISTS `authority-control` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `authority-control`;


-- 导出  表 authority-control.t_permission 结构
CREATE TABLE IF NOT EXISTS `t_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `pid` int(11) NOT NULL COMMENT '父级菜单id',
  `parent_name` varchar(50) DEFAULT NULL COMMENT '父级菜单名称',
  `type` tinyint(4) NOT NULL COMMENT '菜单类型 1:目录 2：菜单 2：按钮',
  `url` varchar(50) DEFAULT NULL COMMENT '菜单url',
  `code` varchar(50) DEFAULT NULL COMMENT '授权编码',
  `color` varchar(32) DEFAULT NULL COMMENT '颜色',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `sort` tinyint(4) NOT NULL COMMENT '排序',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- 正在导出表  authority-control.t_permission 的数据：~15 rows (大约)
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
INSERT INTO `t_permission` (`permission_id`, `name`, `pid`, `parent_name`, `type`, `url`, `code`, `color`, `icon`, `sort`) VALUES
	(1, '系统管理', 0, NULL, 1, NULL, NULL, NULL, NULL, 1),
	(2, '用户管理', 1, '系统管理', 2, 'user/listUI', 'user:listUI', NULL, NULL, 1),
	(3, '新增', 2, '用户管理', 3, NULL, 'user:add', 'btn-primary', 'icon-ok', 2),
	(4, '编辑', 2, '用户管理', 3, NULL, 'user:update', 'btn-warning', 'icon-edit', 3),
	(5, '删除', 2, '用户管理', 3, NULL, 'user:delete', 'btn-danger', 'icon-trash', 4),
	(6, '角色管理', 1, '系统管理', 2, 'role/listUI', 'role:listUI', NULL, NULL, 2),
	(7, '新增', 6, '角色管理', 3, NULL, 'role:add', 'btn-primary', 'icon-ok', 2),
	(8, '编辑', 6, '角色管理', 3, NULL, 'role:update', 'btn-warning', 'icon-edit', 3),
	(9, '删除', 6, '角色管理', 3, NULL, 'role:delete', 'btn-danger', 'icon-trash', 4),
	(10, '权限管理', 1, '系统管理', 2, 'permission/listUI', 'permission:listUI', NULL, NULL, 3),
	(11, '新增', 10, '权限管理', 3, NULL, 'permission:add', 'btn-primary', 'icon-ok', 1),
	(12, '编辑', 10, '权限管理', 3, NULL, 'permission:update', 'btn-warning', 'icon-edit', 2),
	(13, '删除', 10, '权限管理', 3, NULL, 'permission:delete', 'btn-danger', 'icon-trash', 3),
	(14, '设置角色', 2, '用户管理', 3, NULL, 'user:setRole', 'btn-success', 'icon-cog', 1),
	(15, '设置权限', 6, '角色管理', 3, NULL, 'role:setPermission', 'btn-success', 'icon-cog', 1);
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;


-- 导出  表 authority-control.t_role 结构
CREATE TABLE IF NOT EXISTS `t_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `descr` varchar(255) DEFAULT NULL COMMENT '角色说明',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  authority-control.t_role 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` (`role_id`, `name`, `descr`) VALUES
	(1, '超级管理员', '最高权限');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;


-- 导出  表 authority-control.t_role_permission 结构
CREATE TABLE IF NOT EXISTS `t_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- 正在导出表  authority-control.t_role_permission 的数据：~14 rows (大约)
/*!40000 ALTER TABLE `t_role_permission` DISABLE KEYS */;
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES
	(1, 1, 1),
	(2, 1, 2),
	(3, 1, 3),
	(4, 1, 4),
	(5, 1, 5),
	(6, 1, 6),
	(7, 1, 7),
	(8, 1, 8),
	(9, 1, 9),
	(10, 1, 10),
	(11, 1, 11),
	(12, 1, 12),
	(13, 1, 13),
	(14, 1, 14),
	(15, 1, 15);
/*!40000 ALTER TABLE `t_role_permission` ENABLE KEYS */;


-- 导出  表 authority-control.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `salt` varchar(10) DEFAULT NULL COMMENT '盐',
  `status` tinyint(1) NOT NULL COMMENT '状态1:启用 0：禁用',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  authority-control.t_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`user_id`, `user_name`, `password`, `email`, `phone`, `salt`, `status`, `create_time`, `update_time`) VALUES
	(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@163.com', NULL, 'abc', 1, '2017-12-12 12:42:20', '2017-12-12 12:42:22');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;


-- 导出  表 authority-control.t_user_role 结构
CREATE TABLE IF NOT EXISTS `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  authority-control.t_user_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` (`id`, `user_id`, `role_id`) VALUES
	(1, 1, 1);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

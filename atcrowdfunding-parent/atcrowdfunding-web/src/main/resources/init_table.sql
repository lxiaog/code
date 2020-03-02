DROP TABLE
IF
	EXISTS tbl_user;
CREATE TABLE tbl_user (
id INT ( 11 ) NOT NULL PRIMARY KEY auto_increment COMMENT '用户id',
NAME VARCHAR ( 100 ) NOT NULL COMMENT '用户名称',
username VARCHAR ( 100 ) NOT NULL COMMENT '用户名',
PASSWORD VARCHAR ( 100 ) NOT NULL COMMENT '密码',
email VARCHAR ( 100 ) NOT NULL DEFAULT '' COMMENT '角色名称',
create_date datetime NOT NULL COMMENT '创建时间',
update_date datetime NOT NULL COMMENT '更新时间'
);
ALTER TABLE tbl_user COMMENT '用户表';
INSERT INTO tbl_user ( NAME, username, PASSWORD, email, create_date, update_date )
VALUES
	( '超级管理员', 'root', 'root=root', 'root@org.com', NOW( ), NOW( ) );

	INSERT INTO tbl_user ( NAME, username, PASSWORD, email, create_date, update_date )
VALUES
	( '管理员', 'admin', 'admin=admin', 'admin@org.com', NOW( ), NOW( ) );



	DROP TABLE
IF
	EXISTS tbl_role;
CREATE TABLE tbl_role (
id INT ( 11 ) NOT NULL PRIMARY KEY auto_increment COMMENT '角色id',
role_name VARCHAR ( 100 ) NOT NULL COMMENT '角色名称',
create_date datetime NOT NULL COMMENT '创建时间',
update_date datetime NOT NULL COMMENT '更新时间'
);
ALTER TABLE tbl_role COMMENT '角色表';


DROP TABLE
IF
	EXISTS tbl_permission;
CREATE TABLE tbl_permission (
	id VARCHAR ( 32 ) NOT NULL PRIMARY KEY COMMENT 'id主键',
	seqno int(11) not null UNIQUE auto_increment COMMENT '排序编号',
	pid VARCHAR ( 32 ) NOT NULL DEFAULT '' COMMENT '父级菜单id',
	NAME VARCHAR ( 50 ) NOT NULL DEFAULT '' COMMENT '菜单名称',
	url VARCHAR ( 100 ) NOT NULL DEFAULT '' COMMENT '关联的url',
	icon VARCHAR ( 100 ) NOT NULL DEFAULT '' COMMENT '图标',
	LEVEL INT ( 2 ) NOT NULL COMMENT '点击状态 0 父级,依次递增',
	state INT ( 2 ) NOT NULL DEFAULT 0 COMMENT '状态，0启用',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_date datetime NOT NULL COMMENT '更新时间'
);
ALTER TABLE tbl_permission COMMENT '权限表';-- 初始化数据
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( '8e49c4465aea41d2aa22fadbd8b65abf', '', '系统权限菜单', '', 'fa fa-sitemap', 0, 0, NOW( ), NOW( ) );#
-- 控制面板
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( 'd39573e88e364e10a40c5d3439d4de13', '8e49c4465aea41d2aa22fadbd8b65abf', '控制面板', '/web/main', 'glyphicon glyphicon-dashboard', 1, 0, NOW( ), NOW( ) );#
-- 权限管理
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( '90903f569bee4084af7acac2eb948bdf', '8e49c4465aea41d2aa22fadbd8b65abf', '权限管理', '', 'glyphicon glyphicon-tasks', 1, 0, NOW( ), NOW( ) );
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( '1c791c6f98634c1faa22dff61aa99b7a', '90903f569bee4084af7acac2eb948bdf', '用户维护', '/web/user/index', 'glyphicon glyphicon-user', 2, 0, NOW( ), NOW( ) );
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( '9309a5089bd44a2098537235af2540d6', '90903f569bee4084af7acac2eb948bdf', '角色维护', '/web/role/index', 'glyphicon glyphicon-king', 2, 0, NOW( ), NOW( ) );
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( 'b5a4e6cf21c447a787c433f269fd7cc5', '90903f569bee4084af7acac2eb948bdf', '许可维护', '/web/permission/index', 'glyphicon glyphicon-lock', 2, 0, NOW( ), NOW( ) );#
-- 业务审核
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( '0c572e0c16964172a4db2e70b8b119c7', '8e49c4465aea41d2aa22fadbd8b65abf', '业务审核', '/web/main', 'glyphicon glyphicon-ok', 1, 0, NOW( ), NOW( ) );
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( '2188245f15a9423f8e5ac6470684d4fc', '0c572e0c16964172a4db2e70b8b119c7', '实名认证审核', '/web/main', 'glyphicon glyphicon-check', 2, 0, NOW( ), NOW( ) );
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( 'd7882068af36492aa8f78a3b2cc385f6', '0c572e0c16964172a4db2e70b8b119c7', '广告审核', '/web/main', 'glyphicon glyphicon-check', 2, 0, NOW( ), NOW( ) );
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( 'd1466f845ac544a5ab7c8a356b6c6b17', '0c572e0c16964172a4db2e70b8b119c7', '项目审核', '/web/main', 'glyphicon glyphicon-check', 2, 0, NOW( ), NOW( ) );#
-- 	业务管理
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( 'c5cd4f7a5cf94b72bf9059a945cd17df', '8e49c4465aea41d2aa22fadbd8b65abf', '业务管理', '/web/main', 'glyphicon glyphicon-th-large', 1, 0, NOW( ), NOW( ) );
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( '942a4ab948ea427f9b74add481cff7f0', 'c5cd4f7a5cf94b72bf9059a945cd17df', '资质维护', '/web/main', 'glyphicon glyphicon-duplicate', 2, 0, NOW( ), NOW( ) );
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( '371093fad3b142ed924b44c96e1fe720', 'c5cd4f7a5cf94b72bf9059a945cd17df', '分类管理', '/web/main', 'glyphicon glyphicon-equalizer', 2, 0, NOW( ), NOW( ) );
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( '660c208dab364120a7564ec39657d556', 'c5cd4f7a5cf94b72bf9059a945cd17df', '流程管理', '/web/main', 'glyphicon glyphicon-random', 2, 0, NOW( ), NOW( ) );
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( '9611b2236d504e9e89952f9fdbd5aaa8', 'c5cd4f7a5cf94b72bf9059a945cd17df', '广告管理', '/web/main', 'glyphicon glyphicon-hdd', 2, 0, NOW( ), NOW( ) );
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( 'd2bf748e0cfd4198b96238e40d3e0400', 'c5cd4f7a5cf94b72bf9059a945cd17df', '消息模板', '/web/main', 'glyphicon glyphicon-comment', 2, 0, NOW( ), NOW( ) );
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( '8a1a3facdb1b42abbbe8246eb718ea5c', 'c5cd4f7a5cf94b72bf9059a945cd17df', '项目分类', '/web/main', 'glyphicon glyphicon-list', 2, 0, NOW( ), NOW( ) );
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( '2e9ec5a8a88b46498d304eaccaf0056d', 'c5cd4f7a5cf94b72bf9059a945cd17df', '项目标签', '/web/main', 'glyphicon glyphicon-tags', 2, 0, NOW( ), NOW( ) );#
-- 参数管理
INSERT INTO tbl_permission ( id, pid, NAME, url, icon, LEVEL, state, create_date, update_date )
VALUES
	( '60bee940d52f47b1b77883383794234f', '8e49c4465aea41d2aa22fadbd8b65abf', '参数管理', '/web/main', 'glyphicon glyphicon-list-alt', 1, 0, NOW( ), NOW( ) );





	DROP TABLE
IF
	EXISTS tbl_join_user_role;
CREATE TABLE tbl_join_user_role (
id INT ( 11 ) NOT NULL UNIQUE auto_increment COMMENT '用户角色关联表ID',
user_id INT ( 11 ) NOT NULL COMMENT '用户ID',
role_id INT ( 11 ) NOT NULL COMMENT '角色ID',
create_date datetime NOT NULL COMMENT '创建时间',
update_date datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY ( user_id, role_id )
);
ALTER TABLE tbl_join_user_role COMMENT '用户与角色关联表'



DROP TABLE
IF
	EXISTS tbl_join_permission_role;
CREATE TABLE tbl_join_permission_role (
id INT ( 11 ) NOT NULL UNIQUE auto_increment COMMENT '唯一编号ID',
role_id INT ( 11 ) NOT NULL COMMENT '角色id',
permission_id VARCHAR ( 32 ) NOT NULL COMMENT '权限id',
create_date datetime NOT NULL COMMENT '创建时间',
update_date datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY ( role_id, permission_id )
);
ALTER TABLE tbl_join_permission_role COMMENT '权限角色关联表';
INSERT INTO tbl_join_permission_role ( role_id, permission_id,create_date, update_date )
VALUES
	(1,'8e49c4465aea41d2aa22fadbd8b65abf',NOW(),NOW());


	INSERT INTO tbl_join_permission_role ( role_id, permission_id,create_date, update_date )
VALUES
	(1,'d39573e88e364e10a40c5d3439d4de13',NOW(),NOW());


	INSERT INTO tbl_join_permission_role ( role_id, permission_id,create_date, update_date )
VALUES
	(1,'90903f569bee4084af7acac2eb948bdf',NOW(),NOW());


	INSERT INTO tbl_join_permission_role ( role_id, permission_id,create_date, update_date )
VALUES
	(1,'9309a5089bd44a2098537235af2540d6',NOW(),NOW());

	INSERT INTO tbl_join_permission_role ( role_id, permission_id,create_date, update_date )
VALUES
	(1,'b5a4e6cf21c447a787c433f269fd7cc5',NOW(),NOW());
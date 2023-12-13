-- Tenant module begin
DROP TABLE if EXISTS tenant;
CREATE TABLE `tenant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '租户名称',
  `status_code` char(2) NOT NULL COMMENT '租户状态',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='租户表';
-- Tenant module end

-- Orgmnt module begin
DROP TABLE if EXISTS post;
CREATE TABLE `post` (
  `code` char(10) NOT NULL,
  `tenant_id` bigint NOT NULL,
  `name` varchar(50) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB COMMENT='岗位';

DROP TABLE if EXISTS org_type;
CREATE TABLE `org_type` (
  `code` char(10) NOT NULL COMMENT '组织类型编码',
  `tenant_id` bigint NOT NULL,
  `name` varchar(50) NOT NULL,
  `status_code` char(2) NOT NULL DEFAULT 'EF',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB COMMENT='组织类型表';


DROP TABLE if EXISTS org;
CREATE TABLE `org` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint NOT NULL,
  `superior_id` bigint DEFAULT NULL COMMENT '上级组织ID',
  `org_type_code` char(10) NOT NULL COMMENT '组织类型编码',
  `leader_id` bigint DEFAULT NULL COMMENT '负责人ID',
  `name` varchar(50) NOT NULL COMMENT '组织名称',
  `status_code` char(10) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_superior_id` (`superior_id`),
  KEY `idx_org_type_code` (`org_type_code`),
  KEY `idx_leader_id` (`leader_id`)
) ENGINE=InnoDB COMMENT='组织表';


DROP TABLE if EXISTS emp;
CREATE TABLE `emp` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint NOT NULL,
  `org_id` bigint DEFAULT NULL COMMENT '员工所属组织ID',
  `emp_num` varchar(20) NOT NULL COMMENT '员工编号',
  `id_num` varchar(20) NOT NULL COMMENT '身份证号',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `gender_code` varchar(20) DEFAULT NULL COMMENT '性别',
  `dob` date DEFAULT NULL COMMENT '出生日期',
  `status_code` char(3) NOT NULL DEFAULT 'REG',
  `version` bigint DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_org_id` (`org_id`)
) ENGINE=InnoDB COMMENT='员工表';


DROP TABLE if EXISTS skill;
CREATE TABLE `skill_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint NOT NULL,
  `name` varchar(50) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='技能类型表';

DROP TABLE if EXISTS skill;
CREATE TABLE `skill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint NOT NULL,
  `skill_type_id` bigint NOT NULL COMMENT '技能类型ID',
  `emp_id` bigint NOT NULL COMMENT '所属员工ID',
  `level_code` char(3) DEFAULT NULL,
  `duration` int DEFAULT NULL COMMENT '时长',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_emp_id` (`emp_id`)
) ENGINE=InnoDB COMMENT='技能表';

DROP TABLE if EXISTS work_experience;
CREATE TABLE `work_experience` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint NOT NULL,
  `emp_id` bigint NOT NULL,
  `company` varchar(50) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_emp_id` (`emp_id`)
) ENGINE=InnoDB COMMENT='工作经验';


DROP TABLE if EXISTS emp_num_counter;
CREATE TABLE `emp_num_counter` (
  `tenant_id` bigint NOT NULL,
  `year_num` int NOT NULL,
  `max_emp_num` int NOT NULL,
  PRIMARY KEY (`tenant_id`,`year_num`)
) ENGINE=InnoDB COMMENT='员工编号计数表';


DROP TABLE if EXISTS emp_post;
CREATE TABLE `emp_post` (
  `emp_id` bigint NOT NULL COMMENT '员工ID',
  `post_code` char(10) NOT NULL COMMENT '岗位编码',
  `tenant_id` bigint NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`emp_id`,`post_code`),
  KEY `post_code`(`post_code`)
) ENGINE=InnoDB COMMENT='员工岗位';
-- Orgmnt module end

-- Projectmng module begin
DROP TABLE if EXISTS client;
CREATE TABLE `client` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint NOT NULL,
  `curr_mng_id` bigint NOT NULL COMMENT '客户经理ID',
  `name` varchar(10) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id`(`tenant_id`),
  KEY `idx_curr_mng_id`(`curr_mng_id`)
) ENGINE=InnoDB COMMENT='客户';

DROP TABLE if EXISTS contract;
CREATE TABLE `contract` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint NOT NULL,
  `client_id` bigint NOT NULL COMMENT '客户ID',
  `curr_mng_id` bigint NOT NULL COMMENT '合同经理',
  `num` varchar(50) NOT NULL COMMENT '合同编码',
  `name` varchar(50) DEFAULT NULL COMMENT '合同名称',
  `status_code` char(2) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_client_id` (`client_id`),
  KEY `idx_curr_mng_id` (`curr_mng_id`)
) ENGINE=InnoDB COMMENT='合同';

DROP TABLE if EXISTS project;
CREATE TABLE `project` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint NOT NULL,
  `contract_id` bigint NOT NULL COMMENT '合同ID',
  `curr_mng_id` bigint NOT NULL COMMENT '项目经理',
  `num` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `status_code` char(5) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_contract_id` (`contract_id`),
  KEY `idx_curr_mng_id` (`curr_mng_id`)
) ENGINE=InnoDB COMMENT='项目';

DROP TABLE if EXISTS client_mng;
CREATE TABLE `client_mng` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint NOT NULL,
  `client_id` bigint NOT NULL,
  `mng_id` bigint NOT NULL,
  `start_at` datetime NOT NULL,
  `end_at` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_client_id` (`client_id`),
  KEY `idx_mng_id` (`mng_id`)
) ENGINE=InnoDB COMMENT='客户经理关系记录表';



DROP TABLE if EXISTS contract_mng;
CREATE TABLE `contract_mng` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint NOT NULL,
  `contract_id` bigint NOT NULL,
  `mng_id` bigint NOT NULL,
  `start_at` datetime NOT NULL,
  `end_at` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_contract_id` (`contract_id`),
  KEY `idx_mng_id` (`mng_id`)
) ENGINE=InnoDB COMMENT='合同经理关系记录表';



DROP TABLE if EXISTS project_mng;
CREATE TABLE `project_mng` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint NOT NULL,
  `project_id` bigint NOT NULL,
  `mng_id` bigint NOT NULL,
  `start_at` datetime NOT NULL,
  `end_at` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_project_id` (`project_id`),
  KEY `idx_mng_id` (`mng_id`)
) ENGINE=InnoDB COMMENT='项目经理关系记录表';


DROP TABLE if EXISTS project_member;
CREATE TABLE `project_member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint NOT NULL,
  `project_id` bigint NOT NULL,
  `emp_id` bigint NOT NULL,
  `estimate_invest_ratio` smallint NOT NULL COMMENT '预计投入百分比',
  `start_at` datetime NOT NULL,
  `end_at` datetime DEFAULT NULL,
  `status_code` char(2) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_project_id` (`project_id`),
  KEY `idx_emp_id` (`emp_id`)
) ENGINE=InnoDB COMMENT='项目成员';

-- Projectmng module end

-- Effortmng module begin

DROP TABLE if EXISTS effort_record;
CREATE TABLE `effort_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint NOT NULL,
  `project_id` bigint NOT NULL,
  `emp_id` bigint NOT NULL,
  `work_date` date NOT NULL COMMENT '日期',
  `effort` decimal(2,1) NOT NULL COMMENT '工时',
  `notes` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint NOT NULL,
  `last_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_project_id` (`project_id`),
  KEY `idx_emp_id` (`emp_id`)
) ENGINE=InnoDB COMMENT='工时记录';

-- Effortmng module end





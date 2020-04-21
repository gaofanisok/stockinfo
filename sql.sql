drop table if exists stockinfo_userlog;

/*==============================================================*/
/* Table: stockinfo_userlog          后台                       */
/*==============================================================*/
create table stockinfo_userlog
(
   ID_                  varchar(200),
   username             varchar(200),
   password             varchar(200),
   phone                varchar(200),
   state                varchar(20),
   creationtime         datetime
);
drop table if exists stockinfo_userProgram;

/*==============================================================*/
/* Table: stockinfo_userProgram     小程序用户                   */
/*==============================================================*/
create table stockinfo_userProgram
(
   user_id              varchar(200),
   user_name            varchar(200),
   user_phone           varchar(200),
   user_wxid            varchar(200),
   user_tx              varchar(200),
   user_sex             int,
   creationtime         datetime
);

CREATE TABLE  stockinfo_table(
id_ varchar(200) not null PRIMARY KEY,
mc  varchar(200),
gdzjpj varchar(200),
drbh varchar(200),
wrbh varchar(200),
esrbh varchar(200),
bzzf varchar(200),
lx varchar(15),
userid varchar(200),
creationtime         datetime
)
CREATE TABLE  stockinfo_industrytable(
id_ varchar(200) not null PRIMARY KEY,
mc  varchar(200),
gn varchar(200),
ph varchar(200),
ltpj varchar(200),
ltsz varchar(200),
ccjgsl varchar(200),
creationtime         datetime
)
CREATE TABLE  stockinfo_institutionstable(
id_ varchar(200) not null PRIMARY KEY,
mc  varchar(200),
ltpj varchar(200),
bsz varchar(200),
qqjgcgsl varchar(200),
bsqbh varchar(200),
zzjds varchar(200),
lx varchar(20),
userid varchar(200)
)
/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : stockinfo

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-04-21 17:38:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for stockinfo_collect
-- ----------------------------
DROP TABLE IF EXISTS `stockinfo_collect`;
CREATE TABLE `stockinfo_collect` (
  `id` varchar(200) NOT NULL,
  `lx` int(11) DEFAULT NULL,
  `scid` varchar(200) DEFAULT NULL,
  `userid` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

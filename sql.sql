drop table if exists stockinfo_userlog;

/*==============================================================*/
/* Table: stockinfo_userlog                                     */
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

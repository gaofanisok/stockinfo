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
CREATE TABLE  dbo.stocktable(
id_ varchar(200) not null PRIMARY KEY,
mc  varchar(200),
gdzjpj varchar(200),
drbh varchar(200),
wrbh varchar(200),
esrbh varchar(200),
bzzf varchar(200),
lx varchar(15),
userid varchar(200)
)
drop table dbo.institutionstable
select * from dbo.stocktable
CREATE TABLE  dbo.industrytable(
id_ varchar(200) not null PRIMARY KEY,
mc  varchar(200),
gn varchar(200),
ph varchar(200),
ltpj varchar(200),
ltsz varchar(200),
ccjgsl varchar(200),
)
CREATE TABLE  dbo.institutionstable(
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
insert into  dbo.stocktable values('1','一心堂','17737','-23.34%','20.46%','7.50%','','0','');
insert into  dbo.stocktable values('2','达志科技','4505','-20.80%','17.46%','12.50%','','0',''),
('3','怡达股份','12672','-19.80%','59.46%','-0.50%','','0',''),
('4','中飞股份','5800','-15.34%','24.36%','17.50%','','0',''),
('5','飞凯材料','29926','-13.1%','64.46%','17.50%','','0',''),
('6','江丰电子','35946','-21.34%','63.46%','3.50%','','0',''),
('7','美亚电子','15502','-11.34%','5.46%','5.14%','','0','');
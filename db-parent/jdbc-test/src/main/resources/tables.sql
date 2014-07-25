create table t_user(
	id int primary key,
	usercode varchar(20) unique,
	username varchar(20) unique,
	password varchar(20),
	level char(2),
	groupId int
);

create table t_group(
	id int,
	groupcode varchar(20),
	groupname varchar(20)
);
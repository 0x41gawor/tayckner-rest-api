CREATE DATABASE IF NOT EXISTS `tayckner_db`;
USE `tayckner_db`;


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`id` int NOT NULL auto_increment,
`username` varchar(45) NOT NULL unique,
`password` varchar(64) NOT NULL,
`first_name` varchar(45),
`last_name` varchar(45),
`email` varchar(45) NOT NULL unique,

primary key (`id`)
) engine=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
`id` int NOT NULL auto_increment,
`name` varchar(45)  NOT NULL,
`description` varchar(255) default null,
`color` varchar(7)  NOT NULL,
`user_id` int  NOT NULL,

primary key (`id`),

KEY `FK_user_idx` (`user_id`),
CONSTRAINT `FK_user_in_category`
FOREIGN KEY (`user_id`)
REFERENCES `user` (`id`)

on delete no action on update no action
) engine=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
`id` int NOT NULL auto_increment,
`name` varchar(45) NOT NULL,
`category_id` int NOT NULL,
`start_time` datetime NOT NULL,
`end_time` datetime,
`duration` int,
`breaks` int default 0,

primary key (`id`),

KEY `FK_category_idx` (`category_id`),
CONSTRAINT `FK_category`
FOREIGN KEY (`category_id`)
REFERENCES `category` (`id`)

on delete no action on update no action
) engine=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
`id` int NOT NULL auto_increment,
`name` varchar(45) NOT NULL,
`start_time` datetime NOT NULL,
`end_time` datetime,
`duration` int,
`user_id` int  NOT NULL,

primary key (`id`),

KEY `FK_user_idx` (`user_id`),
CONSTRAINT `FK_user_in_schedule`
FOREIGN KEY (`user_id`)
REFERENCES `user` (`id`)

on delete no action on update no action
) engine=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `habit`;
CREATE TABLE `habit` (
`id` int NOT NULL auto_increment,
`name` varchar(45) default null,
`color` varchar(45) default null,
`user_id` int default null,

primary key (`id`),

KEY `FK_user_idx` (`user_id`),

CONSTRAINT `FK_user_in_habit`
FOREIGN KEY (`user_id`)
REFERENCES `user` (`id`)

on delete no action on update no action
) engine=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `habit_event`;
CREATE TABLE `habit_event` (
`id` int NOT NULL auto_increment,
`habit_id` int NOT NULL,
`date` date default null,
`comment` varchar(255) default null,
`value` int default 0,

primary key (`id`),

KEY `FK_habit_idx` (`habit_id`),

CONSTRAINT `FK_habit`
FOREIGN KEY (`habit_id`)
REFERENCES `habit` (`id`)

on delete no action on update no action
) engine=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO `user` VALUES
	(1, 'test_user', 'secret', 'test', 'testowski', 'test@test.pl');

INSERT INTO `category` VALUES
	(1, 'Workout', 'none', '#ffffff', 1);

INSERT INTO `activity` VALUES
	(1, 'Leg day', 1, now(), now(), 10, 0);

INSERT INTO `schedule` VALUES
	(1, 'Leg day', now(), now(), 10, 1);

 INSERT INTO `habit` VALUES
	(1, 'Fast food', '#ffffff', 1);

 INSERT INTO `habit_event` VALUES
	(1, 1, now(), 'My gf fault', 1);

select * from `user`;
select * from `category`;
select * from `activity`;
select * from `schedule`;
select * from `habit`;
select * from `habit_event`;


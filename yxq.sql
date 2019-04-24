/*
SQLyog v10.2 
MySQL - 5.5.53-0ubuntu0.14.04.1-log : Database - yxq
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yxq` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yxq`;

/*Table structure for table `yxq_admin` */

DROP TABLE IF EXISTS `yxq_admin`;

CREATE TABLE `yxq_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `sex` varchar(3) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `status` smallint(2) DEFAULT '0' COMMENT '0正常1删除',
  `role_id` int(11) DEFAULT NULL COMMENT '角色',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像url',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `yxq_admin_ibfk_1` (`role_id`),
  CONSTRAINT `yxq_admin_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `yxq_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `yxq_admin` */

insert  into `yxq_admin`(`id`,`username`,`password`,`phone`,`email`,`sex`,`description`,`status`,`role_id`,`photo`,`update_time`,`create_time`) values (2,'admin','a603870c35c10ee9e44040f96debd0fe',NULL,NULL,'男','超级管理员',0,1,NULL,'2018-11-29 17:45:57','2018-11-29 17:46:03');

/*Table structure for table `yxq_game` */

DROP TABLE IF EXISTS `yxq_game`;

CREATE TABLE `yxq_game` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `game_name` varchar(100) DEFAULT NULL COMMENT '游戏名称',
  `info` text COMMENT '游戏介绍',
  `photo` varchar(255) DEFAULT NULL COMMENT '游戏照片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `order_num` int(10) DEFAULT NULL COMMENT '游戏排序（0表示置顶）',
  `number` int(10) DEFAULT NULL COMMENT '关注人数',
  `status` int(11) DEFAULT '2' COMMENT '0正常1下架2审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

/*Data for the table `yxq_game` */

insert  into `yxq_game`(`id`,`game_name`,`info`,`photo`,`create_time`,`order_num`,`number`,`status`) values (1,'王者荣耀','超辣鸡的游戏...........adasdasdada超辣鸡的游戏...........adasdasdada超辣鸡的游戏...........adasdasdada','https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2826732596,3287633598&fm=26&gp=0.jpg','2018-11-30 20:30:37',9999,888999,0),(2,'英雄联盟','第二辣鸡的游戏。。。','https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1960034857,2738314293&fm=26&gp=0.jpg','2018-11-30 20:31:11',1,1888,0),(34,'决战!平安京','《决战！平安京》是由网易最新推出的《阴阳师》IP公平竞技对战MOBA手游，于2018年1月12日开启公测。','http://image.9game.cn/2017/11/30/18724145.jpg','2018-12-06 00:16:03',9999,123456,0),(35,'绝地求生','这是一场吃鸡大赛！','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=312369779,3674470004&fm=26&gp=0.jpg','2018-12-21 23:21:07',1,232323,0),(36,'炉石传说','这是一个卡牌游戏！','https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1957204712,1828741755&fm=26&gp=0.jpg','2018-12-22 22:36:30',0,0,0),(37,'魔兽世界','这是一场奇幻游戏','https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1311403826,3288396509&fm=26&gp=0.jpg','2018-12-26 11:29:22',0,0,0),(38,'连连看','碰撞，消减。','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545805094239&di=fba012fbf76236b9418c4d9e2689bc5f&imgtype=0&src=http%3A%2F%2Fimg.article.pchome.net%2F00%2F33%2F06%2F67%2Fpic_lib%2Fs960x639%2F11945293524Z1C2Zs960x639.jpg','2018-12-26 11:29:25',0,0,0);

/*Table structure for table `yxq_game_category` */

DROP TABLE IF EXISTS `yxq_game_category`;

CREATE TABLE `yxq_game_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `game_id` bigint(20) DEFAULT NULL COMMENT '该游戏的id',
  `game_name` varchar(100) DEFAULT NULL COMMENT '游戏名称',
  `game_photo` varchar(255) DEFAULT NULL COMMENT '游戏图标',
  `type` varchar(100) DEFAULT NULL COMMENT '游戏类别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

/*Data for the table `yxq_game_category` */

insert  into `yxq_game_category`(`id`,`game_id`,`game_name`,`game_photo`,`type`) values (1,1,'王者荣耀',NULL,'手游'),(2,1,'王者荣耀',NULL,'moba'),(3,2,'英雄联盟',NULL,'moba'),(68,34,'决战!平安京','http://pj7yp5tig.bkt.clouddn.com/Fu61h1r8pKbFlGmuWI-BNS3F4yqj','moba'),(69,34,'决战!平安京','http://pj7yp5tig.bkt.clouddn.com/Fu61h1r8pKbFlGmuWI-BNS3F4yqj','手游'),(70,34,'决战!平安京','http://pj7yp5tig.bkt.clouddn.com/Fu61h1r8pKbFlGmuWI-BNS3F4yqj','角色扮演'),(71,34,'决战!平安京','http://pj7yp5tig.bkt.clouddn.com/Fu61h1r8pKbFlGmuWI-BNS3F4yqj','休闲'),(72,37,'魔兽世界','https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1311403826,3288396509&fm=26&gp=0.jpg','moba'),(73,37,'魔兽世界','https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1311403826,3288396509&fm=26&gp=0.jpg','冒险');

/*Table structure for table `yxq_game_type` */

DROP TABLE IF EXISTS `yxq_game_type`;

CREATE TABLE `yxq_game_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` varchar(100) DEFAULT NULL COMMENT '游戏类别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `yxq_game_type` */

insert  into `yxq_game_type`(`id`,`type`) values (1,'moba'),(2,'手游'),(3,'fps'),(4,'动作'),(5,'冒险'),(6,'模拟'),(7,'角色扮演'),(8,'休闲'),(9,'其他'),(10,'沙盒游戏'),(13,'pc');

/*Table structure for table `yxq_instant_news` */

DROP TABLE IF EXISTS `yxq_instant_news`;

CREATE TABLE `yxq_instant_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `game_id` bigint(20) DEFAULT NULL COMMENT '游戏id',
  `message` text COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `reply_num` int(10) DEFAULT '0' COMMENT '回帖人数',
  `status` int(2) DEFAULT '0' COMMENT '状态 0正常 1过期 2删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

/*Data for the table `yxq_instant_news` */

insert  into `yxq_instant_news`(`id`,`user_id`,`game_id`,`message`,`create_time`,`end_time`,`reply_num`,`status`) values (1,1,1,'我只是想看看我能不能成功显示出来，我希望我能成功！','2018-11-30 21:26:47','2018-11-30 21:26:50',1,1),(2,1,1,'我只是想看看我能不能成功显示出来，我希望我能成功！我是用户2','2018-12-01 00:32:06','2018-12-02 00:20:08',100,1),(3,10,1,'我是子斌，我喜欢打王者荣耀，是人是鬼都在秀，我是子斌，我挨揍！','2018-12-22 19:49:38','2018-12-23 05:49:41',0,2),(4,2,2,'其实我也打英雄联盟，来不来。','2018-12-22 21:26:44','2018-12-23 20:26:47',0,1),(5,1,1,'我就测试一下我可不可以发帖子了','2018-12-23 14:19:23','2018-12-23 14:49:23',0,2),(6,1,2,'我就想测试一下能不能发帖子','2018-12-23 16:16:09','2018-12-24 02:06:09',0,2),(7,1,2,'阿萨大大大大撒大苏打','2018-12-24 00:02:12','2018-12-24 23:52:12',0,2),(14,10,2,'哈哈哈哈哈','2018-12-24 23:56:02','2018-12-25 14:56:02',0,2),(15,10,1,'哈哈哈哈哈哈哈哈','2018-12-25 14:49:30','2018-12-26 13:59:30',0,1),(16,10,2,'测试一下 哈哈哈','2018-12-25 22:10:43','2018-12-26 22:00:43',0,1),(17,14,1,'大家好','2018-12-25 23:58:19','2018-12-26 00:08:19',0,2),(18,11,1,'我也想有人带我玩王者荣耀','2018-12-26 00:03:19','2018-12-26 03:03:19',0,1),(19,11,1,'来咯来咯看看啦啦啦','2018-12-26 00:36:53','2018-12-26 01:26:53',0,1),(20,17,2,'现在立刻马上开黑！','2018-12-26 01:00:52','2018-12-26 03:30:52',0,1),(21,14,1,'。好嘛','2018-12-26 01:19:09','2018-12-26 01:29:09',0,2),(22,14,1,'不好','2018-12-26 01:19:40','2018-12-26 01:29:40',0,2),(23,14,1,'你好你好','2018-12-26 01:22:38','2018-12-26 01:32:38',0,2),(24,14,1,'不不不不','2018-12-26 01:23:34','2018-12-26 01:33:34',0,2),(25,14,1,'不不不不','2018-12-26 01:23:39','2018-12-26 01:33:39',0,2),(26,14,1,'你好吗，不好','2018-12-26 01:42:54','2018-12-26 01:52:54',0,2),(27,14,1,'你好吗，不好','2018-12-26 01:43:15','2018-12-26 01:53:15',0,2),(28,14,1,'啊实打实打算','2018-12-26 01:49:47','2018-12-26 02:19:47',0,2),(29,14,1,'牛牛牛','2018-12-26 01:53:09','2018-12-26 02:03:09',0,2),(30,11,1,'怎么没有人发即时帖子，没人吗，我来了','2018-12-26 14:26:59','2018-12-27 13:26:59',1,2),(31,14,1,'姚泽鑫沙雕','2018-12-26 14:36:39','2018-12-26 15:06:39',0,2),(32,14,34,'官方发广告','2018-12-26 22:42:34','2018-12-27 00:12:34',1,2),(33,14,34,'官方发广告','2018-12-26 22:43:19','2018-12-27 00:13:19',0,2),(34,14,34,'改改改','2018-12-26 22:43:52','2018-12-26 23:03:52',0,2),(35,17,2,'开黑立刻马上','2018-12-26 22:53:19','2018-12-26 23:53:19',3,1),(36,18,34,'cnm','2018-12-26 23:35:42','2018-12-27 22:35:42',0,1),(37,17,34,'？','2018-12-26 23:37:56','2018-12-27 07:37:56',0,1),(38,17,34,'你给我','2018-12-26 23:38:19','2018-12-27 03:38:19',0,1),(39,17,34,'睡醒','2018-12-26 23:38:48','2018-12-27 01:38:48',0,1),(40,14,1,'不保护','2018-12-26 23:39:14','2018-12-26 23:49:14',0,2),(46,14,2,'吃就好','2018-12-27 00:14:40','2018-12-27 00:34:40',0,2),(47,10,1,'上车啦','2018-12-27 15:21:29','2018-12-28 15:01:29',0,1),(48,14,2,'这是一条短期消息','2018-12-31 11:36:22','2018-12-31 11:46:22',1,2),(49,14,1,'你好','2018-12-31 11:54:18','2018-12-31 12:04:18',0,2),(53,10,1,'测试一下','2019-01-03 01:11:31','2019-01-03 23:01:31',0,2),(55,14,36,'听听歌','2019-01-03 01:15:36','2019-01-03 01:25:36',0,2),(56,14,34,'发不了','2019-01-03 01:16:20','2019-01-03 01:26:20',0,2),(57,14,2,'好好好','2019-01-03 01:17:03','2019-01-03 01:27:03',0,2),(59,14,1,'发不了','2019-01-03 01:18:20','2019-01-03 01:38:20',0,2),(61,14,36,'说啥呢','2019-01-03 01:20:05','2019-01-03 01:30:05',0,2),(65,14,35,'对对对','2019-01-03 01:22:31','2019-01-03 01:32:31',0,2),(68,10,37,'来一起玩啊','2019-01-03 01:57:31','2019-01-03 02:47:31',0,2),(69,14,38,'你好','2019-01-03 08:07:01','2019-01-03 08:17:01',0,2),(70,14,38,'你好','2019-01-03 08:07:02','2019-01-03 08:17:02',0,2),(71,14,38,'你好','2019-01-03 08:07:02','2019-01-03 08:17:02',0,2),(72,14,37,'你好','2019-01-03 08:08:16','2019-01-03 08:18:16',0,2),(73,10,1,'哈哈哈 开黑啊','2019-01-03 09:21:59','2019-01-04 02:11:59',0,1),(74,10,2,'有人吗','2019-01-03 09:23:06','2019-01-03 19:23:06',0,1),(75,17,1,'来啊快活啊','2019-01-03 10:18:11','2019-01-03 12:18:11',0,1),(76,17,1,'我带你们飞，我上官婉儿贼666','2019-01-03 10:18:41','2019-01-03 15:18:41',1,1),(77,19,2,'没有','2019-01-03 11:03:20','2019-01-03 12:03:20',0,1),(78,19,2,'大佬们好','2019-01-03 11:04:22','2019-01-04 08:04:22',3,1),(79,23,35,'啦啦啦','2019-01-03 13:33:15','2019-01-03 14:23:15',0,1),(80,23,35,'要不是我菜，谁又愿意当躺狗呢₍⁽˚⑅̆˚⁾₎','2019-01-03 13:35:01','2019-01-03 14:15:01',0,1),(81,23,1,'千万别站在貂蝉大里跟她打','2019-01-03 13:36:52','2019-01-03 14:06:52',0,1),(82,23,1,'千万别站在貂蝉大里跟她打','2019-01-03 13:36:53','2019-01-03 14:06:53',0,1);

/*Table structure for table `yxq_instant_news_reply` */

DROP TABLE IF EXISTS `yxq_instant_news_reply`;

CREATE TABLE `yxq_instant_news_reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `instant_id` bigint(20) DEFAULT NULL COMMENT '对应的即可信息的id',
  `message` text COMMENT '回帖内容',
  `user_id` bigint(20) DEFAULT NULL COMMENT '回帖人的id',
  `create_time` datetime DEFAULT NULL COMMENT '回帖时间',
  `status` int(2) DEFAULT NULL COMMENT '帖子状态,0表示正常，1表示删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `yxq_instant_news_reply` */

insert  into `yxq_instant_news_reply`(`id`,`instant_id`,`message`,`user_id`,`create_time`,`status`) values (1,2,'这条帖子是是关于王者荣耀！',1,'2018-12-01 13:30:37',0),(2,2,'没有小姐姐，不想约！',2,'2018-12-05 22:48:50',0),(3,2,'我是子斌，我让你们带飞！',10,'2018-12-18 15:42:36',0),(5,2,'斤夜太漫长',1,'2018-12-21 15:11:08',0),(6,2,'n我看一下有没有弹窗',1,'2018-12-21 15:16:54',0),(7,2,'成功有没有弹窗啊',1,'2018-12-21 15:17:46',0),(8,2,'成功有没有弹窗啊',1,'2018-12-21 15:19:02',0),(9,2,'成功有没有弹窗啊',1,'2018-12-21 15:19:12',0),(14,2,'急急急急急急急急急',1,'2018-12-21 15:27:29',0),(17,2,'assadadadadadadasdad',1,'2018-12-21 15:33:37',0),(19,6,'我回答了你回答你问题',1,'2018-12-24 00:04:38',0),(20,14,'不好',14,'2018-12-25 14:49:37',0),(21,15,'你好',14,'2018-12-25 23:45:02',0),(22,18,'滚犊子',14,'2018-12-26 00:04:00',0),(23,18,'带我一个！',17,'2018-12-26 00:10:54',0),(24,15,'你好',11,'2018-12-26 00:36:10',0),(25,19,'可口可乐了看看',11,'2018-12-26 00:41:12',0),(26,19,'你好',14,'2018-12-26 01:12:43',0),(27,16,'可以了，知道了。',11,'2018-12-26 14:28:21',0),(28,16,'就不',14,'2018-12-26 16:41:58',0),(29,30,'没有',14,'2018-12-26 16:42:44',0),(30,30,'没有',14,'2018-12-26 16:42:45',0),(31,30,'有',14,'2018-12-26 16:42:59',0),(32,30,'hhh',14,'2018-12-26 16:46:47',0),(37,30,'菜逼不配打游戏',17,'2018-12-26 22:48:09',0),(38,35,'来了来了',14,'2018-12-26 22:53:51',0),(39,35,'我玩亚索贼六',14,'2018-12-26 22:54:10',0),(40,35,'好',17,'2018-12-26 23:07:41',0),(42,48,'不 不是 别瞎说',10,'2018-12-31 11:37:05',0),(43,63,'？？？？又整一堆脏数据？',11,'2019-01-03 01:32:33',0),(44,78,'？？？',17,'2019-01-03 11:23:02',0),(45,76,'假的',23,'2019-01-03 13:34:09',0),(46,78,'大佬们好',20,'2019-01-03 19:43:32',0),(47,78,'好好好',23,'2019-01-03 21:36:34',0);

/*Table structure for table `yxq_long_news` */

DROP TABLE IF EXISTS `yxq_long_news`;

CREATE TABLE `yxq_long_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `message` text COMMENT '帖子内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(2) DEFAULT '0' COMMENT '状态 0正常  1删除',
  `order_num` int(10) DEFAULT '999' COMMENT '排序 算法待定',
  `reply_num` int(10) DEFAULT '0' COMMENT '回帖人数',
  `game_id` bigint(20) DEFAULT NULL COMMENT '游戏id',
  `praise_num` int(10) DEFAULT '0' COMMENT '点赞人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

/*Data for the table `yxq_long_news` */

insert  into `yxq_long_news`(`id`,`user_id`,`message`,`create_time`,`status`,`order_num`,`reply_num`,`game_id`,`praise_num`) values (1,1,'这是个长期帖子！！','2018-11-23 15:13:56',0,999,100,1,20),(2,2,'都没有人回我帖子，生气，伤心！','2018-12-06 00:51:22',0,998,1,1,100),(3,10,'你好啊，不知道你玩不玩英雄脸懵，我想找长期的战友，不喜欢骂人的那种。','2018-12-19 21:32:04',0,998,2,2,122),(4,1,'芭芭拉零八零八零芭芭拉来吧来吧来吧来吧','2018-12-22 15:17:37',0,999,2,1,1),(5,1,'asdsad','2018-12-23 14:21:37',0,0,1,1,0),(6,1,'阿萨大大','2018-12-23 14:24:54',0,999,0,1,0),(7,1,'我发一条长期的来试一下！','2018-12-23 16:16:21',0,1,0,2,0),(8,1,'按时大苏打','2018-12-24 00:02:20',0,999,0,2,0),(9,14,'你好','2018-12-25 00:53:47',1,1,0,2,0),(10,14,'你好啊','2018-12-25 14:48:57',1,999,0,2,0),(11,10,'招人开黑。 有人来吗','2018-12-25 14:55:58',0,999,0,1,0),(12,17,'皮尔特沃夫长期稳定双排','2018-12-26 01:00:23',0,998,1,2,0),(13,11,'high哈','2018-12-26 01:11:40',0,0,0,2,0),(15,14,'你好','2018-12-26 01:52:44',1,1,0,1,0),(16,11,'<view>我没有view另一半','2018-12-26 16:37:00',0,999,0,1,0),(17,11,'<view>我问一下</view>','2018-12-26 16:37:26',0,999,0,1,0),(19,14,'哈哈哈哈','2018-12-26 22:42:16',1,999,0,34,0),(20,14,'反反复复','2018-12-26 22:53:09',1,999,0,1,0),(21,14,'你你你','2018-12-26 23:38:59',1,999,0,1,0),(22,17,'阿破克烈','2018-12-26 23:39:25',0,999,0,34,0),(23,10,'哈哈','2019-01-03 01:11:51',1,999,0,1,0),(24,14,'发不了','2019-01-03 01:16:04',1,999,0,34,0),(25,14,'说什么','2019-01-03 01:17:12',1,999,0,2,0),(26,14,'发不了','2019-01-03 01:18:05',1,999,0,1,0),(29,14,'男生宿舍','2019-01-03 01:21:01',1,999,0,2,0),(34,11,'可以吗','2019-01-03 01:34:46',0,999,0,36,0),(35,11,'可以吗','2019-01-03 01:34:47',0,999,0,36,0),(36,11,'可以吗','2019-01-03 01:34:47',0,999,0,36,0),(38,10,'测试一下长期','2019-01-03 01:57:41',1,1,0,37,0),(39,10,'CESSS','2019-01-03 02:00:54',1,999,0,37,0),(40,14,'你好','2019-01-03 08:06:46',1,999,0,38,0),(41,14,'你好','2019-01-03 08:08:30',0,999,0,37,0),(42,11,'你玩连连看吗','2019-01-03 08:13:51',0,998,1,38,0),(43,19,'嗯哼','2019-01-03 11:01:15',0,997,2,1,0),(44,23,'为什么吃鸡里面没人发帖子。゜(ノ)´Д\'(ヾ)゜。゜','2019-01-03 21:35:12',0,999,0,1,0);

/*Table structure for table `yxq_long_news_reply` */

DROP TABLE IF EXISTS `yxq_long_news_reply`;

CREATE TABLE `yxq_long_news_reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `long_id` bigint(20) DEFAULT NULL COMMENT '对应帖子的id',
  `message` text COMMENT '回帖内容',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '回帖时间',
  `status` int(2) DEFAULT NULL COMMENT '帖子状态，1表示删除，0表示正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `yxq_long_news_reply` */

insert  into `yxq_long_news_reply`(`id`,`long_id`,`message`,`user_id`,`create_time`,`status`) values (1,1,'我觉得好无聊啊，最近都没什么好玩的游戏！',2,'2018-12-02 10:34:19',0),(2,2,'我就是你想带飞的那个小姐姐，快加我，带小姐姐我上钻石吧！',2,'2018-12-02 10:35:33',0),(4,2,'我是子斌，我需要你们的带飞，跪求爸爸们带我飞，谢谢了。',10,'2018-12-19 17:26:23',0),(5,3,'爸爸们，我是认真的啊，真的带我飞可以吗，谢谢了呀，我辅助超级厉害的，不抢经济，不抢人头，是人是狗，都能把我挨揍！',10,'2018-12-20 17:27:27',0),(7,2,'陈工了',1,'2018-12-21 15:45:11',0),(8,2,'我是上墙',1,'2018-12-21 15:52:08',0),(9,5,'你好',11,'2018-12-26 00:36:22',0),(10,11,'爸爸我带你飞！！！给我你的微信号！！',11,'2018-12-26 14:27:58',0),(12,3,'好的呢',17,'2018-12-26 23:11:35',0),(13,2,'可口可乐了',11,'2019-01-01 19:43:37',0),(14,42,'有人吗',11,'2019-01-03 08:13:56',0),(15,5,'回复',14,'2019-01-03 10:47:34',0),(16,43,'回了回了。。。',11,'2019-01-03 11:04:42',0),(17,43,'啊哈',14,'2019-01-03 11:05:26',0),(18,12,'我太菜了不配玩这个',23,'2019-01-03 21:37:13',0);

/*Table structure for table `yxq_message` */

DROP TABLE IF EXISTS `yxq_message`;

CREATE TABLE `yxq_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_id` bigint(20) NOT NULL COMMENT '发送者id',
  `to_id` bigint(20) NOT NULL COMMENT '接收者id',
  `message` varchar(400) DEFAULT NULL COMMENT '消息',
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=431 DEFAULT CHARSET=utf8;

/*Data for the table `yxq_message` */

/*Table structure for table `yxq_message_nosend` */

DROP TABLE IF EXISTS `yxq_message_nosend`;

CREATE TABLE `yxq_message_nosend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_id` bigint(20) DEFAULT NULL,
  `to_id` bigint(20) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `message` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

/*Data for the table `yxq_message_nosend` */

insert  into `yxq_message_nosend`(`id`,`from_id`,`to_id`,`send_time`,`message`) values (10,11,1,'2018-12-26 00:13:00','老看看'),(11,11,1,'2018-12-26 00:13:00','123333兔兔'),(13,11,0,'2018-12-26 01:38:00','老看看'),(14,11,0,'2018-12-26 01:38:00','老看看'),(15,11,0,'2018-12-26 01:38:00','老看看'),(17,11,0,'2018-12-26 02:01:00','可口可乐了'),(18,11,0,'2018-12-26 02:02:00','打两局'),(19,11,0,'2018-12-26 02:02:00','打两局'),(20,11,0,'2018-12-26 02:02:00','j\'lu'),(21,11,0,'2018-12-26 02:02:00','来了来了'),(22,11,0,'2018-12-26 02:06:00','国家科技奖'),(23,11,0,'2018-12-26 02:06:00','来咯来咯看看'),(24,11,0,'2018-12-26 02:06:00','默默哦'),(25,11,0,'2018-12-26 02:12:00','来咯来咯看看'),(26,11,0,'2018-12-26 02:12:00','来咯来咯看看'),(27,11,0,'2018-12-26 02:20:00','图图'),(28,11,0,'2018-12-26 02:21:00','图图旅途了'),(29,11,0,'2018-12-26 02:21:00','图图旅途了'),(30,11,0,'2018-12-26 02:21:00','图图旅途了'),(31,11,0,'2018-12-26 02:21:00','图图旅途了'),(32,11,0,'2018-12-26 02:24:00','5555555555'),(33,11,0,'2018-12-26 02:24:00','刚刚哈哈哈哈哈'),(34,11,0,'2018-12-26 02:24:00','恐惧感觉你爸爸'),(46,18,2,'2018-12-31 17:04:00','cnm'),(47,11,2,'2019-01-01 19:44:00','可口可乐了看看'),(48,11,2,'2019-01-01 19:44:00','他咯'),(50,11,2,'2019-01-02 23:20:00','可口可乐了');

/*Table structure for table `yxq_role` */

DROP TABLE IF EXISTS `yxq_role`;

CREATE TABLE `yxq_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `info` varchar(255) DEFAULT NULL COMMENT '介绍',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `yxq_role` */

insert  into `yxq_role`(`id`,`name`,`info`) values (1,'超级管理员','超级管理员有全部权限');

/*Table structure for table `yxq_user` */

DROP TABLE IF EXISTS `yxq_user`;

CREATE TABLE `yxq_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户的id',
  `username` varchar(100) DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `openid` varchar(100) DEFAULT NULL COMMENT 'openid',
  `nickname` varchar(100) DEFAULT NULL COMMENT '微信昵称',
  `phone` varchar(30) DEFAULT NULL COMMENT '电话',
  `sex` int(2) DEFAULT NULL COMMENT '1表示男，2表示女，0表示保密',
  `headimgurl` varchar(255) DEFAULT NULL COMMENT '头像',
  `location` varchar(100) DEFAULT NULL COMMENT '定位',
  `status` int(2) DEFAULT '0' COMMENT '状态，0表正常，1为停用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `info` varchar(500) DEFAULT NULL COMMENT '个人描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `yxq_user` */

insert  into `yxq_user`(`id`,`username`,`password`,`openid`,`nickname`,`phone`,`sex`,`headimgurl`,`location`,`status`,`create_time`,`info`) values (1,'小宝','123456','aaa','小宝','13327382812',1,'http://ww1.sinaimg.cn/bmiddle/9d5cef69jw1emdriohs6zj20bf0bk759.jpg','广东汕头',0,'2018-12-06 15:15:31','我喜欢玩王者荣耀，请多指教'),(2,'小黑','123456','aaa','小黑','13523273828',2,'http://gss0.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/902397dda144ad34948e2bfcd7a20cf431ad859c.jpg','广东广州',0,'2018-11-16 15:15:34','我喜欢玩英雄联盟，请多多指教！'),(10,'bin',NULL,'oDMB65d-GmU5BFboeaZDPpit9n_I','bin',NULL,1,'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqp98MAhy0sVO4QibGaJiccUSy2P4GRys4kOVViaTpIFhJbNvoxwqCibEJPRoeDsymoibibPVU3YWxCrSgA/132','银河系 地球',0,'2018-12-24 14:01:07','哈哈 一起来开黑 吃鸡'),(11,'_____小爷。',NULL,'oDMB65f8TOJTT4wtKRGxTGzUCMcQ','_____小爷。',NULL,1,'https://wx.qlogo.cn/mmopen/vi_32/8L1dibHCB2OZzDtrLlXjX8ZNZSnGJqCic698WQdNTNmNqMV767a3pxjq1VYbdHqPhbJQTcaX35tb8vnObzbMg30Q/132','广东汕头',0,'2018-12-21 22:13:42','我想要玩王者荣耀，带我飞吧。'),(14,'无则疯',NULL,'oDMB65dhdChudxFTHLRsSkk0tzOc','无则疯',NULL,1,'https://wx.qlogo.cn/mmopen/vi_32/1NMAMj2T81BGLJhsnJ7IZBfQUdnHyLEegMFI22cMxt0hgH6SUTWWBkdiaurVwPNMtWiaXueYFt7M9pb5RNAAe1zw/132','潮州',0,'2018-12-24 13:57:58','风暴英雄要火你你你你你你你你你你你你你你你你你你你你你'),(17,'Wall',NULL,'oDMB65XzmFmPcARYb-gpCfNSSrgA','Wall',NULL,1,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJJfTn8Y30Nu2ONTp1mBr8o7ERkqIoFhgcZGPrbFCQGjWoPicchCdyMSeVF9qibkNkWvR9GlwRIl7Rw/132','广东 广州',0,'2018-12-25 14:58:57','?'),(18,'程',NULL,'oDMB65SBnocKps_oL6iy_KTHGWBs','程',NULL,1,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJGLuZsDDWCZA95Hib7jxWRsFiaNmPcnV6DV520uIpQ4nVv4RAZsBu2C3g40TeIYm8iajLnsIibNVJuzw/132','安徽 芜湖',0,'2018-12-26 23:32:38','还好还好哈还好还好哈'),(19,'小明咔',NULL,'oDMB65YnhkgXTJsnxUKbz10KuYy4','小明咔',NULL,1,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJxQXM5O1ONNtvsqSs78Sr4ffdQwR7xuKMOzxSVqAvOPPZdENO3GgzJWs1uggCnd4ichvusbCjcCeg/132','甲米',0,'2019-01-03 10:57:14',NULL),(20,'字节',NULL,'oDMB65ZTe2tShJOsN0Ea2daq9Qk4','字节',NULL,1,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKwZc62488OibtfruCCcZrNpmtYVL1DNhjZxlibF3QRbibRtGNy16sf9fjJ8At6dZpulG8Za2zSsuiazg/132','广东 湛江',0,'2019-01-03 11:05:49',NULL),(21,'啊信、',NULL,'oDMB65arsyYu2VuG4IHtb4UHrZwg','啊信、',NULL,1,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKCh7lLk0mwaiaLn5ONQNJz5O9ncDu163EcvYDLibhSlrjtUJ28L8AWJTRXHIkZ4snmOl5Cibsyc35uA/132','广东 中山',0,'2019-01-03 11:05:55',NULL),(22,'Zoro-LGL',NULL,'oDMB65cmBoRE_kV5Gsic7lIBGJZc','Zoro-LGL',NULL,1,'https://wx.qlogo.cn/mmopen/vi_32/ymYvic36mNz2OXTfxCuooK5og2mBiaGI8XPXBtmHRiaDx8GVGjEWlzWfdZicMFrFRKF8MW7UfgiczYTdiakOP9SJdE1g/132','贵州 黔南',0,'2019-01-03 11:13:53',NULL),(23,'.',NULL,'oDMB65daxT2HPwjwg731chFgYRkc','.',NULL,1,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKtbmW4uR85sXakzHZJaib1zr8BxMIsHVYyu158FdTNe19meicQ5pic9RicficwTynxvUzXIfZSwwNwKKQ/132','新疆 乌鲁木齐',0,'2019-01-03 11:22:14',NULL),(24,'落落清欢',NULL,'oDMB65TkeJRwjX4wL6adSCHn2d7I','落落清欢',NULL,2,'https://wx.qlogo.cn/mmopen/vi_32/A86hUfNLp7hYjBRib5SITlQwpTncPyrArtnITKJRajZd2Rb0NFUJyibaRZxJZyH9YowyFaW8EdicGrMJ2icTNbwefw/132','广东 湛江',0,'2019-01-03 11:23:38',NULL);

/*Table structure for table `yxq_user_concerns` */

DROP TABLE IF EXISTS `yxq_user_concerns`;

CREATE TABLE `yxq_user_concerns` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_id` bigint(20) DEFAULT NULL COMMENT '关注人id',
  `to_id` bigint(20) DEFAULT NULL COMMENT '被关注人id',
  `createTime` datetime DEFAULT NULL COMMENT '关注时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`from_id`,`to_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

/*Data for the table `yxq_user_concerns` */

insert  into `yxq_user_concerns`(`id`,`from_id`,`to_id`,`createTime`) values (2,2,1,'2018-12-20 10:51:17'),(13,1,10,'2018-12-22 17:39:05'),(23,1,2,'2018-12-23 16:11:57'),(27,10,14,'2018-12-01 00:13:05'),(28,10,11,'2018-12-26 20:00:41'),(33,10,10,'2018-12-06 23:03:17'),(44,17,11,'2018-12-26 22:43:38'),(45,17,10,'2018-12-26 22:45:00'),(46,17,14,'2018-12-26 22:46:43'),(49,17,18,'2018-12-26 23:36:28'),(50,14,18,'2018-12-27 00:13:50'),(51,11,10,'2018-12-29 21:43:17'),(52,14,10,'2018-12-31 11:41:46'),(55,14,11,'2019-01-01 16:30:47'),(56,11,2,'2019-01-01 19:43:21'),(58,11,14,'2019-01-01 19:45:40'),(59,14,17,'2019-01-03 10:46:04'),(60,11,17,'2019-01-03 11:25:44'),(61,11,19,'2019-01-03 11:41:46');

/*Table structure for table `yxq_user_focus` */

DROP TABLE IF EXISTS `yxq_user_focus`;

CREATE TABLE `yxq_user_focus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `game_id` bigint(20) DEFAULT NULL COMMENT '游戏id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`user_id`,`game_id`)
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8;

/*Data for the table `yxq_user_focus` */

insert  into `yxq_user_focus`(`id`,`user_id`,`game_id`) values (39,1,1),(40,1,34),(38,1,35),(41,1,36),(36,1,38),(10,2,1),(11,2,2),(87,10,1),(113,10,2),(101,10,34),(155,10,36),(157,10,37),(156,10,38),(130,11,1),(12,11,2),(139,11,34),(154,11,36),(153,11,37),(147,14,1),(103,14,2),(106,14,34),(149,14,35),(108,14,36),(135,17,1),(142,17,2),(143,17,34),(144,17,35),(138,17,36),(124,17,37),(136,17,38),(140,18,1),(126,18,2),(127,18,34),(162,18,35),(160,18,36),(161,18,37),(163,18,38),(158,19,1),(159,19,2),(167,20,2),(166,20,35),(173,21,35),(165,23,1),(168,23,2),(164,23,35),(169,24,1),(171,24,35),(170,24,38);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
